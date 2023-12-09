package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.domain.model.sample.SampleToken
import co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.ErrorState
import co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.State

// FIXME to parcelable
internal sealed class SampleState : State {
    data object None : SampleState()

    data object Loading : SampleState()

    data class Success(
        val token: SampleToken,
    ) : SampleState()

    data class Error(
        override val throwable: Throwable,
    ) : SampleState(), ErrorState
}
