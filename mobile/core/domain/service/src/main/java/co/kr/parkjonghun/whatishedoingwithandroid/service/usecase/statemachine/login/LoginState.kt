package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.ErrorState
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.State
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.LoginToken
import kotlinx.parcelize.Parcelize

sealed class LoginState : State {
    @Parcelize
    data object None : LoginState()

    @Parcelize
    data object Loading : LoginState()

    @Parcelize
    data class Success(
        val token: LoginToken,
    ) : LoginState()

    @Parcelize
    data class Error(
        override val throwable: Throwable,
    ) : LoginState(), ErrorState
}
