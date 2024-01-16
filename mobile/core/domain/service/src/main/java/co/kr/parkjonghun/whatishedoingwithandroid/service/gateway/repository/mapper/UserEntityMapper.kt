package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.mapper

import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.data.UserDto
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation.AuthCodeDto
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation.TokenDto
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.AuthCode
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.Token
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.User

fun UserDto.domainModel() = User(
    id = id,
    email = email,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun TokenDto.domainMode() = Token(
    accessToken = accessToken,
    providerToken = providerToken,
    refreshToken = refreshToken,
    expiresAt = expiresAt,
    expiresIn = expiresIn,
    tokenType = tokenType,
)

fun AuthCodeDto.domainMode() = AuthCode(
    authCode = authCode,
)
