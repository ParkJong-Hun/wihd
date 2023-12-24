package co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datastore.userDataStore
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.fromJson
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.json
import co.kr.parkjonghun.whatishedoingwithandroid.outside.model.TokenInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserDataStoreDao {
    val tokenInfo: Flow<TokenInfo?>
    suspend fun saveTokenInfo(tokenInfo: TokenInfo)
    suspend fun resetTokenInfo()
}

internal class UserDataStoreDaoImpl(
    private val context: Context,
) : UserDataStoreDao {
    private val userDataStore get() = context.userDataStore

    override val tokenInfo: Flow<TokenInfo?> = userDataStore.data.map {
        it[KEY_TOKEN_INFO]?.fromJson()
    }

    override suspend fun saveTokenInfo(tokenInfo: TokenInfo) {
        userDataStore.edit {
            it[KEY_TOKEN_INFO] = tokenInfo.json()
        }
    }

    override suspend fun resetTokenInfo() {
        userDataStore.edit {
            it.remove(KEY_TOKEN_INFO)
        }
    }

    companion object {
        private val KEY_TOKEN_INFO = stringPreferencesKey("token_info")
    }
}
