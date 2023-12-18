package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.SampleRepository

internal class LoginSideEffectCreator(
    private val sampleRepository: SampleRepository,
) : StateMachine.SideEffectCreator<LoginSideEffect, LoginState, LoginAction> {
    override fun create(state: LoginState, action: LoginAction): LoginSideEffect? =
        when (action) {
            is LoginAction.HogeAction -> LoginSideEffect(sampleRepository = sampleRepository)
            else -> null
        }
}
