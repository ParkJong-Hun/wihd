package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.mapper

import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.data.UserDto
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.User

fun UserDto.domainModel() = User(
    id = id,
    email = email,
    createdAt = createdAt,
    updatedAt = updatedAt,
)
