package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.Action

sealed class SampleAction : Action {
    data object Do : SampleAction()

    internal data class Succeed(val data: String) : SampleAction()
    internal data class Fail(val throwable: Throwable) : SampleAction()

    data class Apply(val fetchedData: String) : SampleAction()

    data object Retry : SampleAction()
    data object ResolveError : SampleAction()
}
