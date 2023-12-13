package co.kr.parkjonghun.whatishedoingwithandroid.inside.di

import co.kr.parkjonghun.whatishedoingwithandroid.inside.repository.SampleRepositoryImpl
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.SampleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    single<SampleRepository> {
        val scope = CoroutineScope(Dispatchers.Main.immediate)
        SampleRepositoryImpl(
            dataSource = get(),
            scope = scope,
        )
    }
}