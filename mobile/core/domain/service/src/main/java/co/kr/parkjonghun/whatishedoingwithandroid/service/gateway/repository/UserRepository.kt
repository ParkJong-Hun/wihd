package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository

import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.User

interface UserRepository {
    suspend fun login()
    suspend fun logout()
    suspend fun getUser(): User
}
