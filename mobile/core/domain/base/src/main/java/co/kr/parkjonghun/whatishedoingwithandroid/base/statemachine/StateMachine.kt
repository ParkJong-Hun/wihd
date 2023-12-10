package co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.coroutines.CoroutineContext

interface StateMachine<STATE : State, ACTION : Action> {
    val currentState: STATE
    val stateFlow: SharedFlow<STATE>
    fun dispatch(action: ACTION) = dispatch(action) {}
    fun dispatch(action: ACTION, after: (Transition<STATE, ACTION>) -> Unit)

    sealed interface Transition<STATE : State, ACTION : Action> {
        val fromState: STATE
        val dispatchedAction: ACTION

        data class Valid<STATE : State, ACTION : Action>(
            override val fromState: STATE,
            val toState: STATE,
            override val dispatchedAction: ACTION,
        ) : Transition<STATE, ACTION>

        data class Invalid<STATE : State, ACTION : Action>(
            override val fromState: STATE,
            override val dispatchedAction: ACTION,
        ) : Transition<STATE, ACTION>
    }

    // TODO Diagram factory
}

@Suppress("UnusedPrivateProperty")
internal class StateMachineImpl<STATE : State, ACTION : Action>(
    name: String,
    initialState: STATE,
    private val sideEffectCreator: SideEffectCreator<SideEffect<STATE, ACTION>, STATE, ACTION>,
) : StateMachine<STATE, ACTION> {
    private val _stateFlow = MutableSharedFlow<STATE>(replay = 1).also { it.tryEmit(initialState) }
    private val MutableSharedFlow<STATE>.value get() = replayCache.first()

    public override val currentState: STATE = _stateFlow.value
    public override val stateFlow: SharedFlow<STATE> = _stateFlow

    private val coroutineContext: CoroutineContext =
        CoroutineName(name) + SupervisorJob() + Dispatchers.Main.immediate
    private val scope: CoroutineScope = CoroutineScope(coroutineContext)

    public override fun dispatch(
        action: ACTION,
        after: (StateMachine.Transition<STATE, ACTION>) -> Unit,
    ) {
        // TODO sideEffect, transition logic
    }
}
