package co.kr.parkjonghun.whatishedoingwithandroid.inside.repository

import co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper.toTokenDto
import co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper.toTokenInfo
import co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper.toUserDto
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.PreferencesDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.RemoteDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.mapper.doaminMode
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.mapper.domainModel
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.Token
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource,
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

    override suspend fun getUser(token: Token): User {
        return withContext(ioScope.coroutineContext) {
            remoteDataSource.getUser(token.toTokenInfo()).toUserDto().domainModel()
        }
    }

    override suspend fun getToken(): Token? {
        return withContext(ioScope.coroutineContext) {
            preferencesDataSource.token.first()?.toTokenDto()?.doaminMode()
        }
    }

    override suspend fun saveTokenAndRetrieveUser(token: Token): User {
        return withContext(ioScope.coroutineContext) {
            preferencesDataSource.saveToken(token.toTokenInfo())
            remoteDataSource.getUser(token.toTokenInfo()).toUserDto().domainModel()
        }
    }
}
