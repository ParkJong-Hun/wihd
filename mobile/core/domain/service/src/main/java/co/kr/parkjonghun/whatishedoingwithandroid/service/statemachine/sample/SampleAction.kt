package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.Action

sealed interface SampleAction : Action {
    data object HogeAction : SampleAction
}
