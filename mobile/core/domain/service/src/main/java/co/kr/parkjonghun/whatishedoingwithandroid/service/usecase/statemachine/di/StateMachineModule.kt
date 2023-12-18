package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.di

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.LoginAction
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.LoginSideEffectCreator
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.LoginState
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.createSampleStateMachine
import org.koin.core.parameter.ParametersHolder
import org.koin.dsl.module

internal val stateMachineModule = module {
    factory<StateMachine<LoginState, LoginAction>> { initialStateHolder ->
        createSampleStateMachine(
            sideEffectCreator = LoginSideEffectCreator(
                sampleRepository = get(),
            ),
            initialState = initialStateHolder.getInitialState(),
        )
    }
}

private fun <T> ParametersHolder.getInitialState(): T? =
    if (size() > 0) this[0] else null
