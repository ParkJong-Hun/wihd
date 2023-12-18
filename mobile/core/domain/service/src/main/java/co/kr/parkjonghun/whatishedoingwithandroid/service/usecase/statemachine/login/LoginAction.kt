package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.Action
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.LoginToken

sealed class LoginAction : Action {
    data object HogeAction : LoginAction()

    internal data class Succeed(
        val token: LoginToken,
    ) : LoginAction()

    internal data class Fail(
        val throwable: Throwable,
    ) : LoginAction()

    data object Resolve : LoginAction()
}
