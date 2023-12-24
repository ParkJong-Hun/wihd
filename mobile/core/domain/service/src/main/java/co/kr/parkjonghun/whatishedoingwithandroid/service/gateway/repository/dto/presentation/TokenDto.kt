package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long,
    val tokenType: String,
) : Parcelable
