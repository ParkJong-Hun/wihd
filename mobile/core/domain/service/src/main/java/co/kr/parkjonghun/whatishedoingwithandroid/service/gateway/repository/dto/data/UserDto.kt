package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.data

import co.kr.parkjonghun.whatishedoingwithandroid.base.model.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDto(
    val id: String,
    val email: String?,
    val createdAt: String?,
    val updatedAt: String?,
) : Entity
