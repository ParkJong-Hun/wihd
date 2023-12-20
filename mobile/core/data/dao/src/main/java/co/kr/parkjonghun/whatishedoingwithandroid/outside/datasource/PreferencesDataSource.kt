package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore.UserDataStoreDao
import kotlinx.coroutines.flow.Flow

/**
 *  Data permanently stored on the device management source.
 */
interface PreferencesDataSource {
    val userId: Flow<String?>
}

internal class PreferencesDataSourceImpl(
    userDataStoreDao: UserDataStoreDao,
) : PreferencesDataSource {
    override val userId: Flow<String?> = userDataStoreDao.userId
}
