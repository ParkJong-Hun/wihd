package co.kr.parkjonghun.whatishedoingwithandroid.domain.usecase.sample

import co.kr.parkjonghun.whatishedoingwithandroid.domain.gateway.repository.SampleRepository
import co.kr.parkjonghun.whatishedoingwithandroid.domain.model.sample.Sample
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
