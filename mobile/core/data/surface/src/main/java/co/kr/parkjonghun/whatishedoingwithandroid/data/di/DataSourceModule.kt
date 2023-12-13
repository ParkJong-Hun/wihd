package co.kr.parkjonghun.whatishedoingwithandroid.data.di

import co.kr.parkjonghun.whatishedoingwithandroid.data.gateway.datasource.PreferencesDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.data.gateway.datasource.PreferencesDataSourceImpl
import co.kr.parkjonghun.whatishedoingwithandroid.data.gateway.datasource.RemoteDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.data.gateway.datasource.RemoteDataSourceImpl
import co.kr.parkjonghun.whatishedoingwithandroid.data.gateway.datasource.SampleDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataSourceModule = module {
    single<PreferencesDataSource> {
        PreferencesDataSourceImpl(
            context = androidContext(),
        )
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl()
    }

    single { SampleDataSource() }
}
