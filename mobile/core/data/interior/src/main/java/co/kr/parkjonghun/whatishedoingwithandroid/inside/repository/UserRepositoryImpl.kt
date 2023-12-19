package co.kr.parkjonghun.whatishedoingwithandroid.inside.repository

import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.RemoteDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.LoginToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val coroutineScope: CoroutineScope,
) : UserRepository {
    override suspend fun login(): LoginToken {
        return withContext(coroutineScope.coroutineContext) {
            LoginToken(token = UUID.randomUUID().toString())
        }
    }

    override suspend fun logout() {
        coroutineScope.launch {
            remoteDataSource.logout()
        }
    }

    // FIXME
    override suspend fun getUser(): Boolean {
        return withContext(coroutineScope.coroutineContext) {
            remoteDataSource.getUser() != null
        }
    }
}
