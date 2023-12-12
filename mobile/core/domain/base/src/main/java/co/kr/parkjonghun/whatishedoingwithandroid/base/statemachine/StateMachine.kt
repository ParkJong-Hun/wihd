package co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine

import co.kr.parkjonghun.whatishedoingwithandroid.base.util.Matcher
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
 * https://en.wikipedia.org/wiki/Finite-state_machine
 */
interface StateMachine<STATE : State, ACTION : Action> {
    val currentState: STATE
    public val flow: SharedFlow<STATE>

    public fun dispatch(
        action: ACTION,
        after: (Transition<STATE, ACTION>) -> Unit = {},
    )

    /**
     *  An action performed when an action occurs in a certain state.
     *  After an action occurs, it always transitions to another state.
     */
    interface SideEffect<STATE : State, ACTION : Action> {
        public suspend fun fire(
            targetStateMachine: StateMachine<STATE, ACTION>,
            validTransition: ValidTransition<STATE, ACTION>,
        )
    }

    /**
     * A condition specifier that specifies which side effect is triggered when a certain action is taken in a certain state.
     */
    interface SideEffectCreator<
        SIDE_EFFECT : SideEffect<STATE, ACTION>,
        STATE : State,
        ACTION : Action,
        > {
        fun create(state: STATE, action: ACTION): SIDE_EFFECT?
    }

    /**
     * Information from which state the action was taken and whether it was valid or not.
     */
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

    /**
     * Diagram of a state machine.
     */
    class Diagram<STATE : State, ACTION : Action>(
        val initialState: STATE,
        val fromStateMap: Map<Matcher<STATE, STATE>, FromState<STATE, ACTION>>,
    ) {
        class FromState<STATE : State, ACTION : Action> {
            class TransitionTo<STATE : State>(val toState: STATE)

            val transitionToStateMap =
                mutableMapOf<Matcher<ACTION, ACTION>, (STATE, ACTION) -> TransitionTo<STATE>>()
        }
    }

    /**
     * Creating a diagram of each state machine.
     */
    class DiagramBuilder<SEALED_STATE : State, SEALED_ACTION : Action>(
        private val initialState: SEALED_STATE,
    ) {
        /**
         * Relationships between states.
         */
        private val relationMap =
            LinkedHashMap<Matcher<SEALED_STATE, SEALED_STATE>, Diagram.FromState<SEALED_STATE, SEALED_ACTION>>()

        @Deprecated(message = "It is not for service.")
        fun <TARGET_STATE : SEALED_STATE> fromState(
            stateMatcher: Matcher<SEALED_STATE, TARGET_STATE>,
            config: RelationBuilder<TARGET_STATE>.() -> Unit,
        ) {
            relationMap[stateMatcher] = RelationBuilder<TARGET_STATE>().apply(config).build()
        }

        public inline fun <reified TARGET_STATE : SEALED_STATE> fromState(
            noinline config: RelationBuilder<TARGET_STATE>.() -> Unit,
        ) = fromState(Matcher.any(), config)

        fun build(): Diagram<SEALED_STATE, SEALED_ACTION> =
            Diagram(initialState, relationMap.toMap())

        /**
         * Creating specifications for what actions should be used when going from one state of each state machine to another.
         */
        inner class RelationBuilder<TARGET_STATE : SEALED_STATE> {
            private val fromStateFactor = Diagram.FromState<SEALED_STATE, SEALED_ACTION>()

            @Suppress("UNCHECKED_CAST")
            @Deprecated(message = "It is not for service.")
            fun <TARGET_ACTION : SEALED_ACTION> on(
                actionMatcher: Matcher<SEALED_ACTION, TARGET_ACTION>,
                transition: TARGET_STATE.(TARGET_ACTION) -> Diagram.FromState.TransitionTo<SEALED_STATE>,
            ) {
                fromStateFactor.transitionToStateMap[actionMatcher] = { state, action ->
                    transition(state as TARGET_STATE, action as TARGET_ACTION)
                }
            }

            public inline fun <reified TARGET_ACTION : SEALED_ACTION> on(
                noinline transition: TARGET_STATE.(TARGET_ACTION) -> Diagram.FromState.TransitionTo<SEALED_STATE>,
            ) {
                on(Matcher.any(), transition)
            }

            public fun TARGET_STATE.transitionTo(newState: SEALED_STATE) =
                Diagram.FromState.TransitionTo(newState)

            public fun TARGET_STATE.transitionToSelf(): Diagram.FromState.TransitionTo<TARGET_STATE> =
                Diagram.FromState.TransitionTo(this)

            fun build() = fromStateFactor
        }
    }
}

@Suppress("UnusedPrivateProperty")
internal class StateMachineImpl<STATE : State, ACTION : Action>(
    name: String,
    initialState: STATE,
    private val sideEffectCreator: StateMachine.SideEffectCreator<out SideEffect<STATE, ACTION>, STATE, ACTION>,
    private val diagram: Diagram<STATE, ACTION>,
) : StateMachine<STATE, ACTION> {
    private val _flow = MutableSharedFlow<STATE>(replay = 1).also { it.tryEmit(initialState) }
    private val MutableSharedFlow<STATE>.value get() = replayCache.first()

    override val currentState: STATE = _flow.value
    override val flow: SharedFlow<STATE> = _flow

    private val stateMachineContext: CoroutineContext =
        CoroutineName(name) + SupervisorJob() + Dispatchers.Main.immediate
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
        newJob { after(transition(action)) }
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
                            dispatchedAction = action,
                        )
                    }
                    ?.also { if (_flow.value != it.toState) _flow.emit(it.toState) }
                    ?: InValidTransition(
                        fromState = currentState,
                        dispatchedAction = action,
                    )
            }
        }.also { transition -> checkFireSideEffect(action, transition) }

    private fun checkFireSideEffect(
        action: ACTION,
        transition: Transition<STATE, ACTION>,
    ) {
        newJob {
            newSideEffect {
                (transition as? ValidTransition<STATE, ACTION>)?.let { validTransition ->
                    sideEffectCreator.create(transition.fromState, action)
                        ?.fire(
                            targetStateMachine = this@StateMachineImpl,
                            validTransition = validTransition,
                        )
                    if (isTerminalState(validTransition.toState)) shutdown()
                }
            }
        }
    }

    private fun newJob(block: suspend CoroutineScope.() -> Unit) =
        stateMachineScope.launch { block() }

    private fun newSideEffect(block: suspend CoroutineScope.() -> Unit) =
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
}

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

typealias Diagram<S, A> = StateMachine.Diagram<S, A>
typealias Transition<S, A> = StateMachine.Transition<S, A>
typealias ValidTransition<S, A> = StateMachine.Transition.Valid<S, A>
typealias InValidTransition<S, A> = StateMachine.Transition.Invalid<S, A>
typealias SideEffect<S, A> = StateMachine.SideEffect<S, A>
