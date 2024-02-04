package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

import kotlin.test.assertTrue

interface StateMachineAsserter<STATE : State, ACTION : Action> {
    suspend fun assertState(
        beforeState: STATE,
        expectedAfterState: STATE? = null,
    )

    suspend fun assertTransition(
        expected: Transition<STATE, ACTION>,
        valid: Boolean,
    )

    suspend fun assertSideEffect(
        state: STATE,
        action: ACTION,
        expected: SideEffect<STATE, ACTION>? = null,
    )
}

internal class StateMachineAsserterImpl<STATE : State, ACTION : Action> :
    StateMachineAsserter<STATE, ACTION> {
    override suspend fun assertState(
        beforeState: STATE,
        expectedAfterState: STATE?,
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun assertTransition(
        expected: Transition<STATE, ACTION>,
        valid: Boolean,
    ) {
        assertTrue {
            if (valid) {
                expected is ValidTransition
            } else {
                expected is InValidTransition
            }
        }
    }

    override suspend fun assertSideEffect(
        state: STATE,
        action: ACTION,
        expected: SideEffect<STATE, ACTION>?,
    ) {
        TODO("Not yet implemented")
    }
}
