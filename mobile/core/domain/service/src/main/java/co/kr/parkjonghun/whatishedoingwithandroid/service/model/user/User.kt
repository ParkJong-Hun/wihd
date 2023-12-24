package co.kr.parkjonghun.whatishedoingwithandroid.service.model.user

import co.kr.parkjonghun.whatishedoingwithandroid.base.model.Entity
import kotlinx.parcelize.Parcelize

/**
 * Logged in user information.
 */
@Parcelize
data class User internal constructor(
    val id: String,
    val email: String?,
    val createdAt: String?,
    val updatedAt: String?,
) : Entity
