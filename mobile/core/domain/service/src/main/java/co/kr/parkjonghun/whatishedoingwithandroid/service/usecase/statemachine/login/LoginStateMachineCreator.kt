package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.createStateMachine

fun createLoginStateMachine(
    sideEffectCreator: StateMachine.SideEffectCreator<LoginSideEffect, LoginState, LoginAction>,
    initialState: LoginState?,
) = createStateMachine(
    name = "Sample",
    initialState = initialState ?: LoginState.None,
    sideEffectCreator = sideEffectCreator,
) {
    fromState<LoginState.None> {
        on<LoginAction.Login> {
            transitionTo(LoginState.Loading)
        }
    }

    fromState<LoginState.Loading> {
        on<LoginAction.Succeed> {
            transitionTo(LoginState.Success)
        }
        on<LoginAction.Fail> {
            transitionTo(LoginState.Error(it.throwable))
        }
    }

    fromState<LoginState.Error> {
        on<LoginAction.Resolve> {
            transitionTo(LoginState.None)
        }
    }
}
