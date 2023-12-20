package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore.UserDataStoreDao
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.Flow

/**
 *  Data permanently stored on the device management source.
 */
interface PreferencesDataSource {
    val userInfo: Flow<UserInfo?>
}

internal class PreferencesDataSourceImpl(
    userDataStoreDao: UserDataStoreDao,
) : PreferencesDataSource {
    override val userInfo: Flow<UserInfo?> = userDataStoreDao.userInfo
}
