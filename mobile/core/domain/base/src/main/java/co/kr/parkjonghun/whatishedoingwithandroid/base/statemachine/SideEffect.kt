package co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine

/**
 *  An action performed when an action occurs in a certain state.
 *  After an action occurs, it always transitions to another state.
 */
fun interface SideEffect<STATE : State, ACTION : Action> {
    fun fire(
        targetStateMachine: StateMachine<STATE, ACTION>,
        validTransition: StateMachine.Transition.Valid<STATE, ACTION>,
    )
}
