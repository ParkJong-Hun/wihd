package co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine

import co.kr.parkjonghun.whatishedoingwithandroid.base.util.Matcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.coroutines.CoroutineContext

/**
 * https://en.wikipedia.org/wiki/Finite-state_machine
 * ref. https://github.com/Tinder/StateMachine/blob/main/src/main/kotlin/com/tinder/StateMachine.kt
 */
interface StateMachine<STATE : State, ACTION : Action> {
    val currentState: STATE
    public val flow: SharedFlow<STATE>
    public fun dispatch(action: ACTION) = dispatch(action) {}
    fun dispatch(action: ACTION, after: (Transition<STATE, ACTION>) -> Unit)

    /**
     *  An action performed when an action occurs in a certain state.
     *  After an action occurs, it always transitions to another state.
     */
    interface SideEffect<STATE : State, ACTION : Action> {
        public fun fire(
            targetStateMachine: StateMachine<STATE, ACTION>,
            validTransition: Transition.Valid<STATE, ACTION>,
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
        val relationMap: Map<Matcher<STATE, STATE>, Relation<STATE, ACTION>>,
    ) {
        class Relation<STATE : State, ACTION : Action> {
            class TransitionTo<STATE : State>(val toState: STATE)

            val transitionMap =
                mutableMapOf<Matcher<ACTION, ACTION>, (STATE, ACTION) -> TransitionTo<STATE>>()
        }
    }

    /**
     * Creating a diagram of each state machine.
     */
    class DiagramBuilder<STATE : State, ACTION : Action>(
        private val initialState: STATE,
    ) {
        private val relationMap =
            LinkedHashMap<Matcher<STATE, STATE>, Diagram.Relation<STATE, ACTION>>()

        @Deprecated(message = "It is not for service.")
        fun <TARGET_STATE : STATE> state(
            stateMatcher: Matcher<STATE, TARGET_STATE>,
            config: RelationConfigBuilder<TARGET_STATE>.() -> Unit,
        ) {
            relationMap[stateMatcher] = RelationConfigBuilder<TARGET_STATE>().apply(config).build()
        }

        public inline fun <reified TARGET_STATE : STATE> state(
            noinline config: RelationConfigBuilder<TARGET_STATE>.() -> Unit,
        ) = state(Matcher.any(), config)

        fun build(): Diagram<STATE, ACTION> = Diagram(initialState, relationMap.toMap())

        /**
         * Creating specifications for what actions should be used when going from one state of each state machine to another.
         */
        inner class RelationConfigBuilder<TARGET_STATE : STATE> {
            private val relationFactor = Diagram.Relation<STATE, ACTION>()

            @Suppress("UNCHECKED_CAST")
            @Deprecated(message = "It is not for service.")
            fun <TARGET_ACTION : ACTION> on(
                actionMatcher: Matcher<ACTION, TARGET_ACTION>,
                transition: TARGET_STATE.(TARGET_ACTION) -> Diagram.Relation.TransitionTo<STATE>,
            ) {
                relationFactor.transitionMap[actionMatcher] = { state, action ->
                    transition(state as TARGET_STATE, action as TARGET_ACTION)
                }
            }

            public inline fun <reified TARGET_ACTION : ACTION> on(
                noinline transition: TARGET_STATE.(TARGET_ACTION) -> Diagram.Relation.TransitionTo<STATE>,
            ) {
                on(Matcher.any(), transition)
            }

            public fun TARGET_STATE.transitionTo(newState: STATE) =
                Diagram.Relation.TransitionTo(newState)

            public fun TARGET_STATE.noTransition(): Diagram.Relation.TransitionTo<TARGET_STATE> =
                Diagram.Relation.TransitionTo(this)

            fun build() = relationFactor
        }
    }
}

@Suppress("UnusedPrivateProperty")
internal class StateMachineImpl<STATE : State, ACTION : Action>(
    name: String,
    initialState: STATE,
    private val sideEffectCreator:
    StateMachine.SideEffectCreator<StateMachine.SideEffect<STATE, ACTION>, STATE, ACTION>,
) : StateMachine<STATE, ACTION> {
    private val _stateFlow = MutableSharedFlow<STATE>(replay = 1).also { it.tryEmit(initialState) }
    private val MutableSharedFlow<STATE>.value get() = replayCache.first()

    override val currentState: STATE = _stateFlow.value
    override val flow: SharedFlow<STATE> = _stateFlow

    private val coroutineContext: CoroutineContext =
        CoroutineName(name) + SupervisorJob() + Dispatchers.Main.immediate
    private val scope: CoroutineScope = CoroutineScope(coroutineContext)

    override fun dispatch(
        action: ACTION,
        after: (StateMachine.Transition<STATE, ACTION>) -> Unit,
    ) {
        // TODO sideEffect, transition logic
    }
}
