package co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper

import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.data.UserDto
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
