package co.kr.parkjonghun.whatishedoingwithandroid.inside.mapper

import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.User
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

private val timeZone = TimeZone.of("Asia/Tokyo")

fun UserInfo.toUser(): User = User(
    id = id,
    email = email,
    createdAt = createdAt?.toLocalDateTime(timeZone).toString(),
    updatedAt = updatedAt?.toLocalDateTime(timeZone).toString(),
)
