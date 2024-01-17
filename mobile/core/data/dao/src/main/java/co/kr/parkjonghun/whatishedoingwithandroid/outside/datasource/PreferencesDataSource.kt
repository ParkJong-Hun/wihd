package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore.UserDataStoreDao
import co.kr.parkjonghun.whatishedoingwithandroid.outside.model.AuthCodeInfo
import kotlinx.coroutines.flow.Flow

/**
 *  Data permanently stored on the device management source.
 */
interface PreferencesDataSource {
    val authCode: Flow<AuthCodeInfo?>
    suspend fun saveAuthCode(authCode: AuthCodeInfo)
    suspend fun resetAuthCode()
}

internal class PreferencesDataSourceImpl(
    private val userDataStoreDao: UserDataStoreDao,
) : PreferencesDataSource {
    override val authCode: Flow<AuthCodeInfo?> = userDataStoreDao.authCodeInfo

    override suspend fun saveAuthCode(authCode: AuthCodeInfo) {
        userDataStoreDao.saveAuthCodeInfo(authCode)
    }

    override suspend fun resetAuthCode() {
        userDataStoreDao.resetAuthCodeInfo()
    }
}
