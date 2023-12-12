package co.kr.parkjonghun.whatishedoingwithandroid.inside.repository

import co.kr.parkjonghun.whatishedoingwithandroid.data.gateway.datasource.SampleDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper.toDomainModel
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.SampleRepository
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.Sample
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.SampleToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import java.util.UUID

class SampleRepositoryImpl(
    private val dataSource: SampleDataSource,
    private val scope: CoroutineScope,
) : SampleRepository {
    override suspend fun getSampleData(): Sample {
        return withContext(scope.coroutineContext) {
            dataSource.getS1().toDomainModel()
        }
    }

    override suspend fun getSampleToken(): SampleToken {
        return withContext(scope.coroutineContext) {
            SampleToken(token = UUID.randomUUID().toString())
        }
    }
}
