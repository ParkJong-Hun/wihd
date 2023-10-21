package co.kr.parkjonghun.whatishedoingwithandroid.mobile.di

import co.kr.parkjonghun.whatishedoingwithandroid.data.datasource.SampleDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.domain.gateway.repository.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideSampleRepository(
        dataSource: SampleDataSource
    ): SampleRepository {
        // FIXME
        return TODO()
        // return SampleRepositoryImpl(dataSource)
    }
}