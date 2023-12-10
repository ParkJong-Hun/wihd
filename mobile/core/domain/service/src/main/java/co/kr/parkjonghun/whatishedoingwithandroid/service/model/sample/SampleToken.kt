package co.kr.parkjonghun.whatishedoingwithandroid.domain.model.sample

import base.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class SampleToken(val token: String) : base.Entity
