package co.kr.parkjonghun.whatishedoingwithandroid.domain.model.sample

import co.kr.parkjonghun.whatishedoingwithandroid.domain.model.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class SampleToken(val token: String) : Entity
