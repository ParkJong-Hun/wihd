package co.kr.parkjonghun.whatishedoingwithandroid.outside.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenInfo(
    val accessToken: String,
    val providerToken: String,
    val refreshToken: String,
    val expiresAt: Long,
    val expiresIn: Long,
    val tokenType: String,
)
