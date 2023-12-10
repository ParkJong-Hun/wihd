package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.SideEffect
import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.StateMachine

internal class SampleSideEffect : SideEffect<SampleState, SampleAction> {
    override fun fire(
        targetStateMachine: StateMachine<SampleState, SampleAction>,
        validTransition: StateMachine.Transition.Valid<SampleState, SampleAction>,
    ) {
        TODO("Not yet implemented")
    }
}
