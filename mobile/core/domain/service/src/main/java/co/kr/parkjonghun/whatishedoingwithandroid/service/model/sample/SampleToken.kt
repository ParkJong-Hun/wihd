package co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.model.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class SampleToken(val token: String) : Entity
