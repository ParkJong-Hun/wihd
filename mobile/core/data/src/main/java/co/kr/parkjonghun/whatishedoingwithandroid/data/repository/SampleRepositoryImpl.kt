package co.kr.parkjonghun.whatishedoingwithandroid.data.repository

import co.kr.parkjonghun.whatishedoingwithandroid.data.datasource.SampleDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.data.mapper.toDomainModel
import co.kr.parkjonghun.whatishedoingwithandroid.domain.gateway.repository.SampleRepository
import co.kr.parkjonghun.whatishedoingwithandroid.domain.model.Sample
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

class SampleRepositoryImpl(
    private val dataSource: SampleDataSource,
    private val scope: CoroutineScope
) : SampleRepository {
    override suspend fun getSampleData(): Sample {
        return withContext(scope.coroutineContext) {
            dataSource.getS1().toDomainModel()
        }
    }
}