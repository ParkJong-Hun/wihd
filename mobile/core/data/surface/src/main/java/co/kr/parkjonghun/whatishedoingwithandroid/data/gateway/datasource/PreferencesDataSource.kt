package co.kr.parkjonghun.whatishedoingwithandroid.data.gateway.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import co.kr.parkjonghun.whatishedoingwithandroid.data.extension.sampleDataStore
import co.kr.parkjonghun.whatishedoingwithandroid.data.extension.userDataStore

/**
 *  Data permanently stored on the device management source.
 */
interface PreferencesDataSource {
    val sampleDataStore: DataStore<Preferences>
    val userDataStore: DataStore<Preferences>
}

internal class PreferencesDataSourceImpl(
    private val context: Context,
) : PreferencesDataSource {
    override val sampleDataStore get() = context.sampleDataStore
    override val userDataStore get() = context.userDataStore
}
