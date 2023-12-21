package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository

internal class LoginSideEffectCreator(
    private val userRepository: UserRepository,
) : StateMachine.SideEffectCreator<LoginSideEffect, LoginState, LoginAction> {
    override fun create(state: LoginState, action: LoginAction): LoginSideEffect? =
        when (action) {
            is LoginAction.Login -> LoginSideEffect(userRepository = userRepository)
            else -> null
        }
}
