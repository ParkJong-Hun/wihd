package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository

import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.Token
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.User

interface UserRepository {
    suspend fun login()
    suspend fun logout()
    suspend fun getUser(token: Token): User?
    suspend fun getToken(): Token?
    suspend fun saveTokenAndRetrieveUser(token: Token): User
}
