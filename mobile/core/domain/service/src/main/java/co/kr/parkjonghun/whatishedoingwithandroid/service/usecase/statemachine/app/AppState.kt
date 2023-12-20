package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.ErrorState
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.State
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.User
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class AppState : State {
    @Parcelize
    data object None : AppState()

    @Parcelize
    data object Loading : AppState()

    @Parcelize
    data object NotLoggedIn : AppState()

    @Parcelize
    data class LoggedIn(
        val currentUser: User,
    ) : AppState()

    @Parcelize
    data class Error(
        override val throwable: Throwable,
    ) : AppState(), ErrorState
}