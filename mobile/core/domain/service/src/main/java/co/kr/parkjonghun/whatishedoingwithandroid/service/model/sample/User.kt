package co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.model.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val email: String?,
    val createdAt: String?,
    val updatedAt: String?,
) : Entity
