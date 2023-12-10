package co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine

/**
 * A condition specifier that specifies which side effect is triggered when a certain action is taken in a certain state.
 */
fun interface SideEffectCreator<
    SIDE_EFFECT : SideEffect<STATE, ACTION>,
    STATE : State,
    ACTION : Action,
    > {
    fun create(state: STATE, action: ACTION): SIDE_EFFECT?
}
