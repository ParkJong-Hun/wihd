package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.di

import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.sample.SampleUseCase
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.sample.SampleUseCaseImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val useCaseModule = module {
    single<SampleUseCase> {
        val scope = CoroutineScope(Dispatchers.Main.immediate)

        SampleUseCaseImpl(
            repository = get(),
            scope = scope,
        )
    }
}