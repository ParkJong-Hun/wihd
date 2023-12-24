package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore.UserDataStoreDao
import co.kr.parkjonghun.whatishedoingwithandroid.outside.model.TokenInfo
import kotlinx.coroutines.flow.Flow

/**
 *  Data permanently stored on the device management source.
 */
interface PreferencesDataSource {
    val token: Flow<TokenInfo?>
    suspend fun saveToken(token: TokenInfo)
}

internal class PreferencesDataSourceImpl(
    private val userDataStoreDao: UserDataStoreDao,
) : PreferencesDataSource {
    override val token: Flow<TokenInfo?> = userDataStoreDao.tokenInfo

    override suspend fun saveToken(token: TokenInfo) {
        userDataStoreDao.saveTokenInfo(token)
    }
}
