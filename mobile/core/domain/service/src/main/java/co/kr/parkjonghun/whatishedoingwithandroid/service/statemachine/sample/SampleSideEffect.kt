package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.SampleRepository

class SampleSideEffect(
    private val sampleRepository: SampleRepository,
) : StateMachine.SideEffect<SampleState, SampleAction> {
    override suspend fun fire(
        targetStateMachine: StateMachine<SampleState, SampleAction>,
        validTransition: StateMachine.Transition.Valid<SampleState, SampleAction>,
    ) {
        runCatching { sampleRepository.getSampleToken() }
            .onSuccess { token ->
                targetStateMachine.dispatch(SampleAction.Succeed(token))
            }
            .onFailure { throwable ->
                targetStateMachine.dispatch(SampleAction.Fail(throwable))
            }
    }
}
