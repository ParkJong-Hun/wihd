package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

import io.mockk.coJustRun

interface StateMachineTester<STATE : State, ACTION : Action> {
    private fun asserter(): StateMachineAsserter<STATE, ACTION> =
        StateMachineAsserterImpl()

    /**
     * @param beforeState target state before dispatching action
     * @param afterState expected state after dispatching action, null is meaning beforeState equals afterState
     * @param action target action to be dispatched
     * @param sideEffect expected sideEffect to be fired
     * @param targetStateMachine target state machine to be tested
     * */
    fun testDispatch(
        beforeState: STATE,
        afterState: STATE?,
        action: ACTION,
        sideEffect: SideEffect<STATE, ACTION>?,
        targetStateMachine: StateMachine<STATE, ACTION>,
    ) = coJustRun {
        /* TODO:
            1. targetSM.dispatch(action)
            2. assertTransition(expectedTransition, actualTransition)
            3. assertSideEffect(beforeState, action, sideEffect)
            4. assertState(beforeState, afterState)
         */
    }
}
