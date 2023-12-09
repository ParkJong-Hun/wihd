package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.SideEffectCreator

internal class SampleSideEffectCreator :
    SideEffectCreator<SampleSideEffect, SampleState, SampleAction> {
    override fun create(state: SampleState, action: SampleAction): SampleSideEffect? {
        TODO("Not yet implemented")
    }
}