package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.sample

import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.SampleRepository
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.Sample
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

interface SampleUseCase {
    suspend operator fun invoke(): Sample
}

class SampleUseCaseImpl(
    private val repository: SampleRepository,
    private val scope: CoroutineScope,
) : SampleUseCase {
    override suspend operator fun invoke(): Sample {
        return withContext(scope.coroutineContext) {
            repository.getSampleData()
        }
    }
}
