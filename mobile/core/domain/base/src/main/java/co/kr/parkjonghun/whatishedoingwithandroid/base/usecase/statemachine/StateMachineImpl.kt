package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.CoroutineContext

/**
 * Create a state machine.
 */
public fun <STATE : State, ACTION : Action> createStateMachine(
    name: String,
    initialState: STATE,
    sideEffectCreator: StateMachine.SideEffectCreator<out SideEffect<STATE, ACTION>, STATE, ACTION>,
    diagramBlock: StateMachine.DiagramBuilder<STATE, ACTION>.() -> Unit,
): StateMachine<STATE, ACTION> =
    StateMachineImpl(
        name = name,
        initialState = initialState,
        sideEffectCreator = sideEffectCreator,
        diagram = StateMachine.DiagramBuilder<STATE, ACTION>(initialState = initialState)
            .apply(diagramBlock)
            .build(),
    )

@Suppress("UnusedPrivateProperty")
internal class StateMachineImpl<STATE : State, ACTION : Action>(
    private val name: String,
    initialState: STATE,
    private val sideEffectCreator: StateMachine.SideEffectCreator<out SideEffect<STATE, ACTION>, STATE, ACTION>,
    private val diagram: Diagram<STATE, ACTION>,
) : StateMachine<STATE, ACTION> {
    private val _flow = MutableSharedFlow<STATE>(replay = 1).also { it.tryEmit(initialState) }
    private val MutableSharedFlow<STATE>.value get() = replayCache.first()

    override val currentState: STATE get() = _flow.value
    override val flow: SharedFlow<STATE> = _flow

    override val composeState: androidx.compose.runtime.State<STATE?>
        @Composable get() = flow.collectAsState(null)

    private val stateMachineContext: CoroutineContext =
        CoroutineName(name) + SupervisorJob() + Dispatchers.Default
    private val stateMachineScope: CoroutineScope = CoroutineScope(stateMachineContext)

    private val sideEffectContext: CoroutineContext =
        CoroutineName("$name-sideEffect") + stateMachineContext
    private val sideEffectScope: CoroutineScope = CoroutineScope(sideEffectContext)

    private val mutex = Mutex()

    private val stateMachineJob: CompletableJob = SupervisorJob(stateMachineContext[Job])

    override fun dispatch(
        action: ACTION,
        after: (Transition<STATE, ACTION>) -> Unit,
    ) {
        stateMachineLaunch { after(transition(action)) }
    }

    private suspend fun transition(action: ACTION): Transition<STATE, ACTION> =
        mutex.withLock {
            _flow.value.let { currentState ->
                diagram.fromStateMap
                    .filterKeys { it.matchAll(currentState) }
                    .values
                    .flatMap { it.transitionToStateMap.entries }
                    .find { it.key.matchAll(action) }
                    ?.let {
                        ValidTransition(
                            fromState = currentState,
                            toState = it.value(currentState, action).toState,
                            targetAction = action,
                        )
                    }
                    ?.also { if (_flow.value != it.toState) _flow.emit(it.toState) }
                    ?: InValidTransition(
                        fromState = currentState,
                        targetAction = action,
                    )
            }
        }.also { transition -> checkFireSideEffect(action, transition) }

    private suspend fun checkFireSideEffect(
        action: ACTION,
        transition: Transition<STATE, ACTION>,
    ) {
        stateMachineLaunch {
            sideEffectLaunch {
                Log.v("SideEffect", "$name ${EMOJI}Before State: ${transition.fromState}")
                (transition as? ValidTransition<STATE, ACTION>)
                    ?.let { validTransition ->
                        Log.v(
                            "SideEffect",
                            "$name $EMOJI  called \"${transition.targetAction}\" action is 【VALID】",
                        )
                        sideEffectCreator.create(transition.fromState, action)
                            ?.fire(
                                targetStateMachine = this@StateMachineImpl,
                                validTransition = validTransition,
                            )
                        if (isTerminalState(validTransition.toState)) shutdown()
                    }
                    ?: run {
                        Log.w(
                            "SideEffect",
                            "$name $EMOJI  called \"${transition.targetAction}\" action is 【INVALID】 from ${transition.fromState}.",
                        )
                    }
                Log.v(
                    "SideEffect",
                    "$name ${EMOJI}After State: $currentState",
                )
            }
        }
    }

    private fun stateMachineLaunch(block: suspend CoroutineScope.() -> Unit) =
        stateMachineScope.launch { block() }

    private fun sideEffectLaunch(block: suspend CoroutineScope.() -> Unit) =
        sideEffectScope.launch { block() }

    private fun isTerminalState(state: STATE): Boolean =
        diagram.fromStateMap
            .filterKeys { it.matchAll(state) }
            .values
            .flatMap { it.transitionToStateMap.entries }
            .isEmpty()

    private fun shutdown() {
        stateMachineJob.cancel()
    }

    companion object {
        private const val EMOJI = "\uD83C\uDFAC"
    }
}
