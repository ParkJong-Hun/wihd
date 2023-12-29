package co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toBoolean
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toByteArray
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toFloat
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toInt
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toStr1ng
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

interface KeyManager {
    fun encryptByteArray(byteArray: ByteArray): ByteArray
    fun decryptAsByteArray(encrypted: ByteArray): ByteArray

    fun encryptInt(int: Int): ByteArray
    fun decryptAsInt(encrypted: ByteArray): Int

    fun encryptFloat(float: Float): ByteArray
    fun decryptAsFloat(encrypted: ByteArray): Float

    fun encryptString(string: String): ByteArray
    fun decryptAsString(encrypted: ByteArray): String

    fun encryptBoolean(boolean: Boolean): ByteArray
    fun decryptAsBoolean(encrypted: ByteArray): Boolean
}

internal class KeyManagerImpl : KeyManager {
    private val keyStore = KeyStore.getInstance(PROVIDER).apply { load(null) }

    override fun encryptByteArray(byteArray: ByteArray): ByteArray {
        return Cipher.getInstance(cipher_transformation)
            .apply { init(Cipher.ENCRYPT_MODE, getSecretKey()) }
            .doFinal(byteArray)
    }

    override fun decryptAsByteArray(encrypted: ByteArray): ByteArray {
        return Cipher.getInstance(cipher_transformation)
            .apply { init(Cipher.DECRYPT_MODE, getSecretKey()) }
            .doFinal(encrypted)
    }

    override fun encryptInt(int: Int): ByteArray {
        return encryptByteArray(int.toByteArray())
    }

    override fun decryptAsInt(encrypted: ByteArray): Int {
        return decryptAsByteArray(encrypted).toInt()
    }

    override fun encryptFloat(float: Float): ByteArray {
        return encryptByteArray(float.toByteArray())
    }

    override fun decryptAsFloat(encrypted: ByteArray): Float {
        return decryptAsByteArray(encrypted).toFloat()
    }

    override fun encryptString(string: String): ByteArray {
        return encryptByteArray(string.toByteArray())
    }

    override fun decryptAsString(encrypted: ByteArray): String {
        return decryptAsByteArray(encrypted).toStr1ng()
    }

    override fun encryptBoolean(boolean: Boolean): ByteArray {
        return encryptByteArray(boolean.toByteArray())
    }

    override fun decryptAsBoolean(encrypted: ByteArray): Boolean {
        return decryptAsByteArray(encrypted).toBoolean()
    }

    private fun getSecretKey(): SecretKey {
        if (!keyStore.containsAlias(KeyAlias.AES.alias)) generateSecretKey()
        return keyStore.getKey(KeyAlias.AES.alias, null) as SecretKey
    }

    private fun generateSecretKey() {
        KeyGenerator.getInstance(secretAlgorithm, PROVIDER).run {
            init(
                KeyGenParameterSpec.Builder(
                    KeyAlias.AES.alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT,
                )
                    .setKeySize(KeyAlias.AES.keySize)
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(ENCRYPTION_PADDING)
                    .setRandomizedEncryptionRequired(false)
                    .build(),
            )
            generateKey()
        }
    }

    companion object {
        private const val PROVIDER = "AndroidKeyStore"
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_ECB
        private const val ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private val secretAlgorithm = KeyAlias.AES.algorithm
        private val cipher_transformation = "$secretAlgorithm/$BLOCK_MODE/$ENCRYPTION_PADDING"
    }
}
