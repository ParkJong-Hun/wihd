package co.kr.parkjonghun.whatishedoingwithandroid.outside.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.userDataStore by preferencesDataStore(name = DataStoreNames.User)
