package co.kr.parkjonghun.whatishedoingwithandroid.mobile.di

import co.kr.parkjonghun.whatishedoingwithandroid.domain.gateway.repository.SampleRepository
import co.kr.parkjonghun.whatishedoingwithandroid.domain.usecase.SampleUseCase
import co.kr.parkjonghun.whatishedoingwithandroid.domain.usecase.SampleUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class, ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideSampleUseCase(
        repository: SampleRepository,
    ): SampleUseCase {
        val scope = CoroutineScope(Dispatchers.Main.immediate)

        return SampleUseCaseImpl(
            repository = repository,
            scope = scope
        )
    }
}