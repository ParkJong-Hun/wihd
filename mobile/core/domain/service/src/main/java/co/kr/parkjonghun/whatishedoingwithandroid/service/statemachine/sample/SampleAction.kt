package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.sample

import base.Action

sealed interface SampleAction : base.Action {
    data object HogeAction : SampleAction
}
