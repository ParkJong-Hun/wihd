package co.kr.parkjonghun.whatishedoingwithandroid.service.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Supabase Access token information.
 */
@Parcelize
data class Token internal constructor(
    val accessToken: String,
    val providerToken: String,
    val refreshToken: String,
    val expiresAt: Long,
    val expiresIn: Long,
    val tokenType: String,
) : Parcelable
