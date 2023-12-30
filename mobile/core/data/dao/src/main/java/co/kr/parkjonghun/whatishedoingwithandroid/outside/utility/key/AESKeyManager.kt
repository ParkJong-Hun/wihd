package co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

interface AESKeyManager {
    val secretKey: SecretKey
    val cipherTransformation: String
}

class AESKeyManagerImpl : AESKeyManager {
    private val keyStore = KeyStore.getInstance(ANDROID_PROVIDER).apply { load(null) }

    override val secretKey: SecretKey
        get() {
            if (!keyStore.containsAlias(KeyConfig.AES.alias)) generateSecretKey()
            return keyStore.getKey(KeyConfig.AES.alias, null) as SecretKey
        }

    override val cipherTransformation =
        "${KeyConfig.AES.algorithm}/$BLOCK_MODE_ECB/$ENCRYPTION_PADDING_PKCS7"

    private fun generateSecretKey() {
        KeyGenerator.getInstance(KeyConfig.AES.algorithm, ANDROID_PROVIDER).run {
            init(
                KeyGenParameterSpec.Builder(
                    KeyConfig.AES.alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT,
                )
                    .setKeySize(KeyConfig.AES.keySize)
                    .setBlockModes(BLOCK_MODE_ECB)
                    .setEncryptionPaddings(ENCRYPTION_PADDING_PKCS7)
                    .setRandomizedEncryptionRequired(false)
                    .build(),
            )
            generateKey()
        }
    }

    companion object {
        const val ANDROID_PROVIDER = "AndroidKeyStore"
        const val BLOCK_MODE_ECB = KeyProperties.BLOCK_MODE_ECB
        const val ENCRYPTION_PADDING_PKCS7 = KeyProperties.ENCRYPTION_PADDING_PKCS7
    }
}
