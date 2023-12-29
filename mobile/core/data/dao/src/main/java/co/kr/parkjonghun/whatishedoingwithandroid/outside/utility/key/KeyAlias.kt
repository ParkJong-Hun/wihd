package co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key

import android.security.keystore.KeyProperties

enum class KeyAlias(
    val alias: String,
    val algorithm: String,
    val keySize: Int,
) {
    AES("real", KeyProperties.KEY_ALGORITHM_AES, 256),
}
