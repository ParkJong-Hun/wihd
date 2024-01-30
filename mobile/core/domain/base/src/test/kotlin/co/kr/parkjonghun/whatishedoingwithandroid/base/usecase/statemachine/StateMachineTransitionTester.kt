package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

interface StateMachineTransitionTester<STATE : State, ACTION : Action> {
    fun testTransition(
        beforeSTATE: STATE,
        afterState: STATE?,
        action: ACTION,
        targetStateMachine: StateMachine<STATE, ACTION>,
    ) =
}
