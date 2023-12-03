package co.kr.parkjonghun.whatishedoingwithandroid.data.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import co.kr.parkjonghun.whatishedoingwithandroid.data.model.DataStores

val Context.sampleDataStore: DataStore<Preferences> by preferencesDataStore(name = DataStores.Sample)
val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = DataStores.User)
