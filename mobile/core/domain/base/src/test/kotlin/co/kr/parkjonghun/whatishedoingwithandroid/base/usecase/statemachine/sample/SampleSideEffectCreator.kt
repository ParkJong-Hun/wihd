package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine

internal class SampleSideEffectCreator :
    StateMachine.SideEffectCreator<SampleSideEffect, SampleState, SampleAction> {
    override fun create(state: SampleState, action: SampleAction): SampleSideEffect? =
        when (action) {
            is SampleAction.Do -> {
                if (state is SampleState.None) {
                    SampleSideEffect
                } else {
                    null
                }
            }

            is SampleAction.Retry -> {
                if (state is SampleState.Error) {
                    SampleSideEffect
                } else {
                    null
                }
            }

            else -> null
        }
}
