package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

/**
 * A case for test dispatching an [Action] to a [StateMachine].
 *
 * @param beforeState target state before dispatching action
 * @param afterState expected state after dispatching action,
 * null is meaning beforeState equals afterState
 * @param action target action to be dispatched
 * @param sideEffect expected sideEffect to be fired
 * @param creator [createStateMachine] might take over the [beforeState]
 */
data class StateMachineTestCase<STATE : State, ACTION : Action>(
    val beforeState: STATE,
    val afterState: STATE?,
    val action: ACTION,
    val sideEffect: SideEffect<STATE, ACTION>?,
    val creator: (initialState: STATE) -> StateMachine<STATE, ACTION>,
)
