package co.kr.parkjonghun.whatishedoingwithandroid.outside.extension

import android.util.Base64
import java.nio.ByteBuffer
import java.nio.ByteOrder

fun ByteArray.toInt(): Int {
    val decodedBytes = Base64.decode(this, Base64.DEFAULT)
    return ByteBuffer.wrap(decodedBytes).order(ByteOrder.nativeOrder()).int
}

fun Int.toByteArray(): ByteArray {
    val byteBuffer = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder())
    byteBuffer.putInt(this)
    return Base64.encode(byteBuffer.array(), Base64.DEFAULT)
}

fun ByteArray.toFloat(): Float {
    val decodedBytes = Base64.decode(this, Base64.DEFAULT)
    return ByteBuffer.wrap(decodedBytes).order(ByteOrder.nativeOrder()).float
}

fun Float.toByteArray(): ByteArray {
    val byteBuffer = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder())
    byteBuffer.putFloat(this)
    return Base64.encode(byteBuffer.array(), Base64.DEFAULT)
}

fun ByteArray.toBoolean(): Boolean {
    val decodedBytes = Base64.decode(this, Base64.DEFAULT)
    return decodedBytes[0].toInt() != 0
}

fun Boolean.toByteArray(): ByteArray {
    return Base64.encode(byteArrayOf(if (this) 1.toByte() else 0.toByte()), Base64.DEFAULT)
}

fun ByteArray.toStr1ng(): String {
    val decodedBytes = Base64.decode(this, Base64.DEFAULT)
    return String(decodedBytes, Charsets.UTF_8)
}

fun String.toByteArray(): ByteArray {
    return Base64.encode(this.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
}