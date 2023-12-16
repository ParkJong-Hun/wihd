package co.kr.parkjonghun.whatishedoingwithandroid.outside.di

import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.PreferencesDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.PreferencesDataSourceImpl
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.RemoteDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.RemoteDataSourceImpl
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.SampleDataSource
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
