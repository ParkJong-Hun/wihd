package co.kr.parkjonghun.whatishedoingwithandroid.outside.dao

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datastore.sampleDataStore
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datastore.userDataStore

interface DataStoreDao {
    val sampleDataStore: DataStore<Preferences>
    val userDataStore: DataStore<Preferences>
}

internal class DataStoreDaoImpl(
    private val context: Context,
) : DataStoreDao {
    override val sampleDataStore get() = context.sampleDataStore
    override val userDataStore get() = context.userDataStore
}