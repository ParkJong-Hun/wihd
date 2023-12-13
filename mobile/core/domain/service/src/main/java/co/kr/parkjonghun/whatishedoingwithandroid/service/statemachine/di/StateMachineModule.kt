package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.di

import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample.SampleSideEffectCreator
import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample.createSampleStateMachine
import org.koin.dsl.module

val stateMachineModule = module {
    factory {
        createSampleStateMachine(
            sideEffectCreator = SampleSideEffectCreator(
                sampleRepository = get(),
            ),
            initialState = it[0],
        )
    }
}
