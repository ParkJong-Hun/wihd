package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine

internal class SampleSideEffectCreator :
    StateMachine.SideEffectCreator<SampleSideEffect, SampleState, SampleAction> {
    override fun create(state: SampleState, action: SampleAction): SampleSideEffect? =
        when (action) {
            is SampleAction.Do,
            is SampleAction.Retry,
            -> SampleSideEffect()

            else -> null
        }
}
