package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

import io.mockk.coJustRun

interface StateMachineTester<STATE : State, ACTION : Action> {
    private fun asserter(): StateMachineAsserter<STATE, ACTION> = StateMachineAsserterImpl()

    /**
     * this is the [StateMachine.SideEffectCreator] will be used in [testDispatch].
     */
    fun targetSideEffectCreator(): StateMachine.SideEffectCreator<out SideEffect<STATE, ACTION>, STATE, ACTION>

    /**
     * this is the [StateMachine.ReactiveEffect] will be used in [testDispatch].
     */
    fun targetReactiveEffect(): ReactiveEffect<STATE, ACTION>? = null

    /**
     * [StateMachine.Transition] validations and [StateMachine.SideEffectCreator]s are checked
     * to ensure that the both [afterState] and [sideEffect] are as expected.
     *
     * @param action target action to be dispatched
     * @param beforeState target state before dispatching action
     * @param afterState expected state after dispatching action,
     * null is meaning beforeState equals afterState
     * @param sideEffect expected sideEffect to be fired
     * @param targetStateMachine target state machine to be tested
     * */
    fun testDispatch(
        action: ACTION,
        beforeState: STATE,
        afterState: STATE?,
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
