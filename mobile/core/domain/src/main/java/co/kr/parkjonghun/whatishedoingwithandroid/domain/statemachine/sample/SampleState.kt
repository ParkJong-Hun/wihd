package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.domain.model.sample.SampleToken
import co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.ErrorState
import co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.State
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
