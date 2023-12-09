package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine

fun interface SideEffect<STATE : State, ACTION : Action> {
    fun fire(
        targetStateMachine: StateMachine<STATE, ACTION>,
        validTransition: StateMachine.Transition.Valid<STATE, ACTION>,
    )
}
