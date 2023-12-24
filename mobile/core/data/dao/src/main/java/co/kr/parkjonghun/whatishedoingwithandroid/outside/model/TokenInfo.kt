package co.kr.parkjonghun.whatishedoingwithandroid.outside.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TokenInfo(
    val accessToken: String,
    val providerToken: String,
    val refreshToken: String,
    val expiresAt: Long,
    val expiresIn: Long,
    val tokenType: String,
) : Parcelable
