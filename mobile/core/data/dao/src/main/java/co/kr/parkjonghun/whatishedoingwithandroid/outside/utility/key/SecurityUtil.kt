package co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key

import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toBoolean
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toByteArray
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toByteArrayUTF8
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toFloat
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toInt
import co.kr.parkjonghun.whatishedoingwithandroid.outside.extension.toStringUTF8
import javax.crypto.Cipher

interface SecurityUtil {
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

internal class SecurityUtilImpl(
    private val aesKeyManager: AESKeyManager,
) : SecurityUtil {
    override fun encryptByteArray(byteArray: ByteArray): ByteArray {
        return Cipher.getInstance(aesKeyManager.cipherTransformation)
            .apply { init(Cipher.ENCRYPT_MODE, aesKeyManager.secretKey) }
            .doFinal(byteArray)
    }

    override fun decryptAsByteArray(encrypted: ByteArray): ByteArray {
        return Cipher.getInstance(aesKeyManager.cipherTransformation)
            .apply { init(Cipher.DECRYPT_MODE, aesKeyManager.secretKey) }
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
        return encryptByteArray(string.toByteArrayUTF8())
    }

    override fun decryptAsString(encrypted: ByteArray): String {
        return decryptAsByteArray(encrypted).toStringUTF8()
    }

    override fun encryptBoolean(boolean: Boolean): ByteArray {
        return encryptByteArray(boolean.toByteArray())
    }

    override fun decryptAsBoolean(encrypted: ByteArray): Boolean {
        return decryptAsByteArray(encrypted).toBoolean()
    }
}
