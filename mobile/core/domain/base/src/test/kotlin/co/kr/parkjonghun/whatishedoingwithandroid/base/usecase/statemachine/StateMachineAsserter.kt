package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

import kotlin.test.assertEquals
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
        creator: StateMachine.SideEffectCreator<out SideEffect<STATE, ACTION>, STATE, ACTION>,
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
        creator: StateMachine.SideEffectCreator<out SideEffect<STATE, ACTION>, STATE, ACTION>,
    ) {
        val actual = creator.create(state, action)
        assertEquals(
            expected = expected,
            actual = actual,
        )
    }
}
