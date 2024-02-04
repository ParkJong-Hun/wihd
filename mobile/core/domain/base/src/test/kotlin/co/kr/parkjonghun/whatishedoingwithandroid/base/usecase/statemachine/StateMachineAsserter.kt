package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

import kotlin.test.assertTrue

interface StateMachineAsserter<STATE : State, ACTION : Action> {
    suspend fun assertState(
        beforeState: STATE,
        expectedAfterState: STATE? = null,
    )

    suspend fun assertTransition(
        expectedValidation: Boolean,
        actual: Transition<STATE, ACTION>,
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
        expectedValidation: Boolean,
        actual: Transition<STATE, ACTION>,
    ) {
        assertTrue {
            if (expectedValidation) {
                actual is ValidTransition
            } else {
                actual is InValidTransition
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
