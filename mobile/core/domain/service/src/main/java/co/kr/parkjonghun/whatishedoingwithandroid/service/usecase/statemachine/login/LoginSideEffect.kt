package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository

class LoginSideEffect(
    private val userRepository: UserRepository,
) : StateMachine.SideEffect<LoginState, LoginAction> {
    override suspend fun fire(
        targetStateMachine: StateMachine<LoginState, LoginAction>,
        validTransition: StateMachine.Transition.Valid<LoginState, LoginAction>,
    ) {
        runCatching { userRepository.login() }
            .onSuccess { token ->
                targetStateMachine.dispatch(LoginAction.Succeed(token))
            }
            .onFailure { throwable ->
                targetStateMachine.dispatch(LoginAction.Fail(throwable))
            }
    }
}
