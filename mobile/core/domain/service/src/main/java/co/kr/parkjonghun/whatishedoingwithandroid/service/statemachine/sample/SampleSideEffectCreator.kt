package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.sample

import base.SideEffectCreator

internal class SampleSideEffectCreator :
    base.SideEffectCreator<SampleSideEffect, SampleState, SampleAction> {
    override fun create(state: SampleState, action: SampleAction): SampleSideEffect? {
        TODO("Not yet implemented")
    }
}
