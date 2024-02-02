package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

interface StateMachineAsserter<STATE : State, ACTION : Action> {
    suspend fun assertState(
        beforeState: STATE,
        expectedAfterState: STATE? = null,
    )

    suspend fun assertTransition(
        expected: Transition<STATE, ACTION>,
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
        expected: Transition<STATE, ACTION>,
        actual: Transition<STATE, ACTION>,
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun assertSideEffect(
        state: STATE,
        action: ACTION,
        expected: SideEffect<STATE, ACTION>?,
    ) {
        TODO("Not yet implemented")
    }
}
