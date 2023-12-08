package co.kr.parkjonghun.whatishedoingwithandroid.data.di

import android.content.Context
import co.kr.parkjonghun.whatishedoingwithandroid.data.datasource.PreferencesDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.data.datasource.PreferencesDataSourceImpl
import co.kr.parkjonghun.whatishedoingwithandroid.data.datasource.RemoteDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.data.datasource.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@Suppress("unused")
object DataModule {
    @Provides
    @Singleton
    fun providePreferencesDataSource(
        @ApplicationContext context: Context,
    ): PreferencesDataSource {
        return PreferencesDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSourceImpl()
    }
}
