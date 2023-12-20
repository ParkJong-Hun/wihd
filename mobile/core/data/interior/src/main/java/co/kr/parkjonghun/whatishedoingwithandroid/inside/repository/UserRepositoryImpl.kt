package co.kr.parkjonghun.whatishedoingwithandroid.inside.repository

import co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper.toUser
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.PreferencesDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.RemoteDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val coroutineScope: CoroutineScope,
) : UserRepository {
    override suspend fun login() {
        return withContext(coroutineScope.coroutineContext) {
            remoteDataSource.login()
        }
    }

    override suspend fun logout() {
        coroutineScope.launch {
            remoteDataSource.logout()
        }
    }

    override suspend fun getUser(): User {
        return preferencesDataSource.userInfo.first()?.toUser() ?: error("User is not logged in.")
    }
}
