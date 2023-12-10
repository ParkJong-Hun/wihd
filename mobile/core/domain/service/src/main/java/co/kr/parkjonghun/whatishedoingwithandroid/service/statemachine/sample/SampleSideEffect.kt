package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine.sample

import base.SideEffect
import base.StateMachine

internal class SampleSideEffect : base.SideEffect<SampleState, SampleAction> {
    override fun fire(
        targetStateMachine: base.StateMachine<SampleState, SampleAction>,
        validTransition: base.StateMachine.Transition.Valid<SampleState, SampleAction>,
    ) {
        TODO("Not yet implemented")
    }
}
