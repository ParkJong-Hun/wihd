package co.kr.parkjonghun.whatishedoingwithandroid.outside.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthCodeInfo(
    val authCode: String,
)
