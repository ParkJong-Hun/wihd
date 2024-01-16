package co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import co.kr.parkjonghun.whatishedoingwithandroid.outside.datastore.userDataStore
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.decryptAnyAsJson
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.encryptAnyAsJson
import co.kr.parkjonghun.whatishedoingwithandroid.outside.model.AuthCodeInfo
import co.kr.parkjonghun.whatishedoingwithandroid.outside.model.TokenInfo
import co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key.SecurityUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

interface UserDataStoreDao {
    val tokenInfo: Flow<TokenInfo?>
    val authCodeInfo: Flow<AuthCodeInfo?>

    suspend fun saveTokenInfo(tokenInfo: TokenInfo)
    suspend fun resetTokenInfo()

    suspend fun saveAuthCodeInfo(authCodeInfo: AuthCodeInfo)
    suspend fun resetAuthCodeInfo()
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

    override val authCodeInfo: Flow<AuthCodeInfo?> = userDataStore.data.map {
        it[KEY_AUTH_CODE_INFO]?.let { encryptedString ->
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

    override suspend fun saveAuthCodeInfo(authCodeInfo: AuthCodeInfo) {
        userDataStore.edit {
            it[KEY_AUTH_CODE_INFO] = securityUtil.encryptAnyAsJson(tokenInfo)
        }
    }

    override suspend fun resetAuthCodeInfo() {
        Timber.w("your token is invalid. So, will reset the token stored in the device.")
        userDataStore.edit {
            it.remove(KEY_AUTH_CODE_INFO)
        }
    }

    companion object {
        private val KEY_TOKEN_INFO = stringPreferencesKey("token_info")
        private val KEY_AUTH_CODE_INFO = stringPreferencesKey("auth_code_info")
    }
}
