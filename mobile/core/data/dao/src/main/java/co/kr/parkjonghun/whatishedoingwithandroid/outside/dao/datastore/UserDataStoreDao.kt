package co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datastore.userDataStore
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.fromJson
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.json
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserDataStoreDao {
    val userInfo: Flow<UserInfo?>
    suspend fun saveUserId(userInfo: UserInfo)
}

internal class UserDataStoreDaoImpl(
    private val context: Context,
) : UserDataStoreDao {
    private val userDataStore get() = context.userDataStore

    override val userInfo: Flow<UserInfo?> = userDataStore.data.map {
        it[KEY_USER_ID]?.fromJson()
    }

    override suspend fun saveUserId(userInfo: UserInfo) {
        userDataStore.edit {
            it[KEY_USER_ID] = userInfo.json()
        }
    }

    companion object {
        private val KEY_USER_ID = stringPreferencesKey("user_id")
    }
}
