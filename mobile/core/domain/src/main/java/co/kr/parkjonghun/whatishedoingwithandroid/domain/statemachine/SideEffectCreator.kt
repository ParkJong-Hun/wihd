package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine

fun interface SideEffectCreator<
    SIDE_EFFECT : SideEffect<STATE, ACTION>,
    STATE : State,
    ACTION : Action,
    > {
    fun create(state: STATE, action: ACTION): SIDE_EFFECT?
}
