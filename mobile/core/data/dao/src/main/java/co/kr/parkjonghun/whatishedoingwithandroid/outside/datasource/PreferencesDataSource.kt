package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.DataStoreDao

/**
 *  Data permanently stored on the device management source.
 */
interface PreferencesDataSource

internal class PreferencesDataSourceImpl(
    private val dataStoreDao: DataStoreDao,
) : PreferencesDataSource {
    // TODO
}
