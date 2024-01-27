package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository

import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.User
import kotlinx.coroutines.flow.SharedFlow

interface UserRepository {
    val currentUser: SharedFlow<User?>
    suspend fun login()
    suspend fun logout()
    suspend fun getUser(): User?
}
