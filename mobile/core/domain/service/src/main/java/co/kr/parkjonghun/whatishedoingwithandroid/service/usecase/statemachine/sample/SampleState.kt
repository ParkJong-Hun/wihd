package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.ErrorState
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.State
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.SampleToken
import kotlinx.parcelize.Parcelize

sealed class SampleState : State {
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
