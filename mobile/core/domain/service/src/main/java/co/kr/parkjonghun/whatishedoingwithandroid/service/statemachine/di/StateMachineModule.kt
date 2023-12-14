package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.di

import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample.SampleAction
import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample.SampleSideEffectCreator
import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample.SampleState
import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample.createSampleStateMachine
import org.koin.core.parameter.ParametersHolder
import org.koin.dsl.module

val stateMachineModule = module {
    factory<StateMachine<SampleState, SampleAction>> { initialStateHolder ->
        createSampleStateMachine(
            sideEffectCreator = SampleSideEffectCreator(
                sampleRepository = get(),
            ),
            initialState = initialStateHolder.getInitialState(),
        )
    }
}

private fun <T> ParametersHolder.getInitialState(): T? =
    if (size() > 0) this[0] else null
