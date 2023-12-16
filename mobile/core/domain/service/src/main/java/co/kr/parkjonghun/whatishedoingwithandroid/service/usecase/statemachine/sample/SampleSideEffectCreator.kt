package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.SampleRepository

internal class SampleSideEffectCreator(
    private val sampleRepository: SampleRepository,
) : StateMachine.SideEffectCreator<SampleSideEffect, SampleState, SampleAction> {
    override fun create(state: SampleState, action: SampleAction): SampleSideEffect? =
        when (action) {
            is SampleAction.HogeAction -> SampleSideEffect(sampleRepository = sampleRepository)
            else -> null
        }
}
