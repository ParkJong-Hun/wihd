package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.createStateMachine

fun createSampleStateMachine(
    sideEffectCreator: StateMachine.SideEffectCreator<LoginSideEffect, LoginState, LoginAction>,
    initialState: LoginState?,
) = createStateMachine(
    name = "Sample",
    initialState = initialState ?: LoginState.None,
    sideEffectCreator = sideEffectCreator,
) {
    fromState<LoginState.None> {
        on<LoginAction.HogeAction> {
            transitionTo(LoginState.Loading)
        }
    }

    fromState<LoginState.Loading> {
        on<LoginAction.Succeed> {
            transitionTo(LoginState.Success(it.token))
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
