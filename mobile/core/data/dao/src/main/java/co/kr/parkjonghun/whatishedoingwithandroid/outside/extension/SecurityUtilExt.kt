package co.kr.parkjonghun.whatishedoingwithandroid.outside.extension

import co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key.SecurityUtil

/**
 * @return ByteArray converted to a string.
 */
inline fun <reified T> SecurityUtil.encryptAnyAsJson(any: T): String {
    val json = any.json()
    val encrypted = encryptString(json)
    return encrypted.joinToString(",")
}

/**
 * @param encryptedString This is a ByteArray converted to a string.
 */
inline fun <reified T> SecurityUtil.decryptAnyAsJson(encryptedString: String): T {
    val encrypted = encryptedString.split(",").map { it.toByte() }.toByteArray()
    val json = decryptAsString(encrypted)
    return json.fromJson()
}
