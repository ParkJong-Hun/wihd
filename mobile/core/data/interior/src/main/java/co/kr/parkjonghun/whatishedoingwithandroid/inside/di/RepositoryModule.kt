package co.kr.parkjonghun.whatishedoingwithandroid.inside.di

import co.kr.parkjonghun.whatishedoingwithandroid.inside.repository.UserRepositoryImpl
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> {
        UserRepositoryImpl(
            remoteDataSource = get(),
            preferencesDataSource = get(),
            coroutineScope = CoroutineScope(Dispatchers.Default),
        )
    }
}
