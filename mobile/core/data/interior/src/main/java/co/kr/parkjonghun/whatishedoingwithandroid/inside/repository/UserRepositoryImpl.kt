package co.kr.parkjonghun.whatishedoingwithandroid.inside.repository

import co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper.toAuthCodeInfo
import co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper.toTokenDto
import co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper.toUserDto
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.PreferencesDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource.RemoteDataSource
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.mapper.domainMode
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.mapper.domainModel
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.AuthCode
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

    override suspend fun getUser(authCode: AuthCode): User? {
        return withContext(ioScope.coroutineContext) {
            getUserInternal(authCode)
        }
    }

    override suspend fun getAuthCode(): AuthCode? {
        return withContext(ioScope.coroutineContext) {
            preferencesDataSource.authCode.first()?.toTokenDto()?.domainMode()
        }
    }

    override suspend fun saveAuthCodeAndRetrieveUser(authCode: AuthCode): User {
        return withContext(ioScope.coroutineContext) {
            preferencesDataSource.saveAuthCode(authCode.toAuthCodeInfo())
            getUserInternal(authCode)
        }
    }

    private suspend fun getUserInternal(authCode: AuthCode): User {
        return withContext(ioScope.coroutineContext) {
            runCatching {
                remoteDataSource.getUser(authCode.toAuthCodeInfo()).toUserDto().domainModel()
            }.onFailure {
                preferencesDataSource.resetAuthCode()
            }.getOrThrow()
        }
    }
}
