package co.kr.parkjonghun.whatishedoingwithandroid.outside.model

import kotlinx.serialization.Serializable

@Deprecated(
    message = "use UserSession instead",
    replaceWith = ReplaceWith("io.github.jan.supabase.gotrue.user.UserSession"),
)
@Serializable
data class TokenInfo(
    val accessToken: String,
    val providerToken: String,
    val refreshToken: String,
    val expiresAt: Long,
    val expiresIn: Long,
    val tokenType: String,
)
