package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.mapper

import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.data.UserDto
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation.TokenDto
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.Token
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.User

fun UserDto.domainModel() = User(
    id = id,
    email = email,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun TokenDto.doaminMode() = Token(
    accessToken = accessToken,
    refreshToken = refreshToken,
    expiresIn = expiresIn,
    tokenType = tokenType,
)