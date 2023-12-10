package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository

import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.Sample

interface SampleRepository {
    suspend fun getSampleData(): Sample
}
