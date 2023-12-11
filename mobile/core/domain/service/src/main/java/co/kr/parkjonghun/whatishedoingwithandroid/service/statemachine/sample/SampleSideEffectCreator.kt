package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.StateMachine

internal class SampleSideEffectCreator :
    StateMachine.SideEffectCreator<SampleSideEffect, SampleState, SampleAction> {
    override fun create(state: SampleState, action: SampleAction): SampleSideEffect? =
        when (action) {
            is SampleAction.HogeAction -> SampleSideEffect()
            else -> null
        }
}
