package co.kr.parkjonghun.whatishedoingwithandroid.data.di

import android.content.Context
import co.kr.parkjonghun.whatishedoingwithandroid.data.datasource.PreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
@Suppress("unused")
object DataModule {
    @Provides
    fun providePreferencesDataSource(
        @ApplicationContext context: Context,
    ): PreferencesDataSource {
        return PreferencesDataSource(context)
    }
}
