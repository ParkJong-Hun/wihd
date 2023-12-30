package co.kr.parkjonghun.whatishedoingwithandroid.service.model.user

import co.kr.parkjonghun.whatishedoingwithandroid.base.model.Entity
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
) : Entity
