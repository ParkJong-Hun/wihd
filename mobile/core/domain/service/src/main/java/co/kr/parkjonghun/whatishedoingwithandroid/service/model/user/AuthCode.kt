package co.kr.parkjonghun.whatishedoingwithandroid.service.model.user

import co.kr.parkjonghun.whatishedoingwithandroid.base.model.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthCode internal constructor(
    val authCode: String,
) : Entity
