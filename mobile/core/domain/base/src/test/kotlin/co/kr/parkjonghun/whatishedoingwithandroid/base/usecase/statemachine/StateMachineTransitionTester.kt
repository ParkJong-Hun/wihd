package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

import io.mockk.coJustRun

interface StateMachineTransitionTester<STATE : State, ACTION : Action> {
    fun testTransition(
        beforeState: STATE,
        afterState: STATE?,
        action: ACTION,
        targetStateMachine: StateMachine<STATE, ACTION>,
    ) = coJustRun {
        /* TODO:
            1. get targetSM.currentState
            2. assertEquals(beforeState, targetSM.currentState)
            3. get targetSM.currentState
            4. targetSM.dispatch(action)
            5. check targetSM.dispatch(action) validation
            6. if(valid) 7-1 else 7-2
            7-1. assertEquals(afterState, targetSM.currentState)
            7-2. assertEquals(afterState, afterState)
         */
    }
}
