package co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datastore.userDataStore
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.decryptAnyAsJson
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.encryptAnyAsJson
import co.kr.parkjonghun.whatishedoingwithandroid.outside.model.TokenInfo
import co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key.SecurityUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

interface UserDataStoreDao {
    val tokenInfo: Flow<TokenInfo?>
    suspend fun saveTokenInfo(tokenInfo: TokenInfo)
    suspend fun resetTokenInfo()
}

internal class UserDataStoreDaoImpl(
    private val context: Context,
    private val securityUtil: SecurityUtil,
) : UserDataStoreDao {
    private val userDataStore get() = context.userDataStore

    override val tokenInfo: Flow<TokenInfo?> = userDataStore.data.map {
        it[KEY_TOKEN_INFO]?.let { encryptedString ->
            securityUtil.decryptAnyAsJson(encryptedString)
        }
    }

    override suspend fun saveTokenInfo(tokenInfo: TokenInfo) {
        userDataStore.edit {
            it[KEY_TOKEN_INFO] = securityUtil.encryptAnyAsJson(tokenInfo)
        }
    }

    override suspend fun resetTokenInfo() {
        Timber.w("your token is invalid. So, will reset the token stored in the device.")
        userDataStore.edit {
            it.remove(KEY_TOKEN_INFO)
        }
    }

    companion object {
        private val KEY_TOKEN_INFO = stringPreferencesKey("token_info")
    }
}
