package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.di

import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample.SampleSideEffectCreator
import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample.createSampleStateMachine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class, ViewModelComponent::class)
@Suppress("unused")
class StateMachineModule {
    @Provides
    fun provideSampleStateMachine() {
        createSampleStateMachine(
            sideEffectCreator = SampleSideEffectCreator(),
            initialState = null,
        )
    }
}
