package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.StateMachine

class SampleSideEffect : StateMachine.SideEffect<SampleState, SampleAction> {
    override fun fire(
        targetStateMachine: StateMachine<SampleState, SampleAction>,
        validTransition: StateMachine.Transition.Valid<SampleState, SampleAction>,
    ) {
        TODO("Not yet implemented")
    }
}
