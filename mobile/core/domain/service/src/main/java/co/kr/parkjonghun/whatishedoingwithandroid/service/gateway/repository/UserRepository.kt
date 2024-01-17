package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository

import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.AuthCode
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.User

interface UserRepository {
    suspend fun login()
    suspend fun logout()
    suspend fun getUser(authCode: AuthCode): User?
    suspend fun getAuthCode(): AuthCode?
    suspend fun saveAuthCodeAndRetrieveUser(authCode: AuthCode): User
}
