package co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key

import android.security.keystore.KeyProperties

enum class KeyConfig(
    val alias: String,
    val algorithm: String,
    val keySize: Int,
) {
    /**
     * AES 256.
     */
    AES("for_aes", KeyProperties.KEY_ALGORITHM_AES, 256),
}
