package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.Action
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.SampleToken

sealed class SampleAction : Action {
    data object HogeAction : SampleAction()

    internal data class Succeed(
        val token: SampleToken,
    ) : SampleAction()

    internal data class Fail(
        val throwable: Throwable,
    ) : SampleAction()

    data object Resolve : SampleAction()
}
