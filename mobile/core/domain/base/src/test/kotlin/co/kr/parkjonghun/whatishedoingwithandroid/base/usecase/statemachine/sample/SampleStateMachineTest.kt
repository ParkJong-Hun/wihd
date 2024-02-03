package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.ReactiveEffect
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.SideEffect
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachineTester

internal class SampleStateMachineTest : StateMachineTester<SampleState, SampleAction> {
    override fun targetSideEffectCreator(): StateMachine.SideEffectCreator<out SideEffect<SampleState, SampleAction>, SampleState, SampleAction> {
        return SampleSideEffectCreator()
    }

    override fun targetReactiveEffect(): ReactiveEffect<SampleState, SampleAction> {
        return SampleReactiveEffect()
    }
}
