package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.Action

sealed class LoginAction : Action {
    data object Login : LoginAction()

    internal data object Succeed : LoginAction()

    internal data class Fail(
        val throwable: Throwable,
    ) : LoginAction()

    data object Resolve : LoginAction()
}
