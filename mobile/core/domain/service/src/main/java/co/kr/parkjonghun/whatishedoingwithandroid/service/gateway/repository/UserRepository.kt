package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository

import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.LoginToken

interface UserRepository {
    suspend fun login(): LoginToken
    suspend fun logout()
    suspend fun getUserId(): String?
}
