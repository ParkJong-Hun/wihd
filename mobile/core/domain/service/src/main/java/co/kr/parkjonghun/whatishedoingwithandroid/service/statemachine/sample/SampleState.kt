package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.ErrorState
import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.State
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.SampleToken
import kotlinx.parcelize.Parcelize

internal sealed class SampleState : State {
    @Parcelize
    data object None : SampleState()

    @Parcelize
    data object Loading : SampleState()

    @Parcelize
    data class Success(
        val token: SampleToken,
    ) : SampleState()

    @Parcelize
    data class Error(
        override val throwable: Throwable,
    ) : SampleState(), ErrorState
}
