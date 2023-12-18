package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository

import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.Sample
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.LoginToken

interface SampleRepository {
    suspend fun getSampleData(): Sample
    suspend fun getSampleToken(): LoginToken
}
