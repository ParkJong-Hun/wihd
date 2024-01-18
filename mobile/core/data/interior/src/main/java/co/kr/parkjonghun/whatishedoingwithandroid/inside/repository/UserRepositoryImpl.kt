package co.kr.parkjonghun.whatishedoingwithandroid.inside.repository

import co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper.toUserDto
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.RemoteDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.mapper.domainModel
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val ioScope: CoroutineScope,
) : UserRepository {
    override suspend fun login() {
        return withContext(ioScope.coroutineContext) {
            remoteDataSource.login()
        }
    }

    override suspend fun logout() {
        ioScope.launch {
            remoteDataSource.logout()
        }
    }

    override suspend fun getUser(): User? {
        return withContext(ioScope.coroutineContext) {
            remoteDataSource.getUser()?.toUserDto()?.domainModel()
        }
    }
}
