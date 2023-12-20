package co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datastore.userDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserDataStoreDao {
    val userId: Flow<String?>
    suspend fun saveUserId(userId: String)
}

internal class UserDataStoreDaoImpl(
    private val context: Context,
) : UserDataStoreDao {
    private val userDataStore get() = context.userDataStore

    override val userId = userDataStore.data.map {
        it[KEY_USER_ID]
    }

    override suspend fun saveUserId(userId: String) {
        userDataStore.edit {
            it[KEY_USER_ID] = userId
        }
    }

    companion object {
        private val KEY_USER_ID = stringPreferencesKey("user_id")
    }
}
