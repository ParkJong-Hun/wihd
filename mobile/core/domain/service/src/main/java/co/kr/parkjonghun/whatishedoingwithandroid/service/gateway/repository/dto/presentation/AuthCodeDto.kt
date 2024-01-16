package co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthCodeDto(
    val authCode: String,
) : Parcelable
