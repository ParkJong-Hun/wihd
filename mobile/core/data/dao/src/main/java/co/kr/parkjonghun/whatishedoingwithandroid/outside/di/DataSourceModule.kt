package co.kr.parkjonghun.whatishedoingwithandroid.outside.di

import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.PreferencesDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.PreferencesDataSourceImpl
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.RemoteDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.RemoteDataSourceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataSourceModule = module {
    single<PreferencesDataSource> {
        PreferencesDataSourceImpl(
            userDataStoreDao = get(),
        )
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl(
            supabaseDao = get(),
            coroutineScope = CoroutineScope(Dispatchers.IO),
        )
    }
}
