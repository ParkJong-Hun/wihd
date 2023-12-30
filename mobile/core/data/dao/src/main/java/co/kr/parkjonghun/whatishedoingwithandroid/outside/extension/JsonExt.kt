package co.kr.parkjonghun.whatishedoingwithandroid.outside.extension

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.json(): String = Json.encodeToString(this)
inline fun <reified T> String.fromJson(): T = Json.decodeFromString(this)
