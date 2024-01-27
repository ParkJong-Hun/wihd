package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.ErrorState
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.State
import kotlinx.parcelize.Parcelize

sealed class SampleState : State {
    @Parcelize
    data object None : SampleState()

    @Parcelize
    data object Loading : SampleState()

    sealed class Stable : SampleState() {
        abstract val data: String

        @Parcelize
        data class Waiting(override val data: String) : Stable()

        @Parcelize
        data class Success(override val data: String) : Stable()
    }

    @Parcelize
    data class Error(
        override val throwable: Throwable,
    ) : SampleState(), ErrorState
}
