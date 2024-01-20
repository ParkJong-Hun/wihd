package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.di

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app.AppAction
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app.AppSideEffectCreator
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app.AppState
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app.createAppStateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.LoginAction
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.LoginSideEffectCreator
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.LoginState
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.createLoginStateMachine
import org.koin.core.parameter.ParametersHolder
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val stateMachineModule = module {
    factory<StateMachine<AppState, AppAction>>(
        qualifier = named("App"),
    ) { initialStateHolder ->
        createAppStateMachine(
            sideEffectCreator = AppSideEffectCreator(
                userRepository = get(),
            ),
            initialState = initialStateHolder.getInitialState(),
        )
    }

    factory<StateMachine<LoginState, LoginAction>>(
        qualifier = named("Login"),
    ) { initialStateHolder ->
        createLoginStateMachine(
            sideEffectCreator = LoginSideEffectCreator(
                userRepository = get(),
            ),
            initialState = initialStateHolder.getInitialState(),
        )
    }
}

private fun <T> ParametersHolder.getInitialState(): T? =
    if (size() > 0) this[0] else null
