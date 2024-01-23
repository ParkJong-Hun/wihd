package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore.UserDataStoreDao

/**
 *  Data permanently stored on the device management source.
 */
interface PreferencesDataSource {
    // TODO
}

@Suppress("UnusedPrivateProperty")
internal class PreferencesDataSourceImpl(
    private val userDataStoreDao: UserDataStoreDao,
) : PreferencesDataSource {
    // TODO
}

internal class FakePreferencesDataSourceImpl : PreferencesDataSource {
    // TODO
}
