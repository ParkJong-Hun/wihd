package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.Action

sealed interface SampleAction : Action {
    data object HogeAction : SampleAction
}
