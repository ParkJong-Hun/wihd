package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

import app.cash.turbine.test
import kotlinx.coroutines.flow.Flow
import kotlin.test.assertEquals
import kotlin.test.assertTrue

interface StateMachineAsserter<STATE : State, ACTION : Action> {
    suspend fun Flow<STATE>.assertState(
        beforeState: STATE,
        afterState: STATE?,
    )

    fun assertTransition(
        expectedValidation: Boolean,
        actual: Transition<STATE, ACTION>,
    )

    fun assertSideEffect(
        state: STATE,
        action: ACTION,
        expected: SideEffect<STATE, ACTION>? = null,
        creator: StateMachine.SideEffectCreator<out SideEffect<STATE, ACTION>, STATE, ACTION>,
    )

    fun assertReactiveEffect(
        state: STATE,
        action: ACTION,
        expected: ReactiveEffect<STATE, ACTION>? = null,
        reactiveEffect: StateMachine.ReactiveEffect<STATE, ACTION>?,
    )
}

internal class StateMachineAsserterImpl<STATE : State, ACTION : Action> :
    StateMachineAsserter<STATE, ACTION> {
    override suspend fun Flow<STATE>.assertState(
        beforeState: STATE,
        afterState: STATE?,
    ) {
        test {
            assertEquals(beforeState, awaitItem())
            if (afterState != null) {
                assertEquals(afterState, awaitItem())
            } else {
                expectNoEvents()
            }
        }
    }

    override fun assertTransition(
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

    override fun assertSideEffect(
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

    override fun assertReactiveEffect(
        state: STATE,
        action: ACTION,
        expected: ReactiveEffect<STATE, ACTION>?,
        reactiveEffect: StateMachine.ReactiveEffect<STATE, ACTION>?,
    ) {
        // TODO
    }
}
