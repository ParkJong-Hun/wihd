package co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper

import co.kr.parkjonghun.whatishedoingwithandroid.outside.model.TokenInfo
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.data.UserDto
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation.TokenDto
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.Token
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

private val timeZone = TimeZone.of("Asia/Tokyo")

internal fun UserInfo.toUserDto(): UserDto = UserDto(
    id = id,
    email = email,
    createdAt = createdAt?.toLocalDateTime(timeZone).toString(),
    updatedAt = updatedAt?.toLocalDateTime(timeZone).toString(),
)

internal fun Token.toTokenInfo(): TokenInfo = TokenInfo(
    accessToken = accessToken,
    providerToken = providerToken,
    refreshToken = refreshToken,
    expiresAt = expiresAt,
    expiresIn = expiresIn,
    tokenType = tokenType,
)

internal fun TokenInfo.toTokenDto(): TokenDto = TokenDto(
    accessToken = accessToken,
    providerToken = providerToken,
    refreshToken = refreshToken,
    expiresAt = expiresAt,
    expiresIn = expiresIn,
    tokenType = tokenType,
)
