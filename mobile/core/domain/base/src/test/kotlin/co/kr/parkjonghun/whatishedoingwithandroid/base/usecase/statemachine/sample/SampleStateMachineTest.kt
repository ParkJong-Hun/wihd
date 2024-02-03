package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachineTester

internal class SampleStateMachineTest : StateMachineTester<SampleState, SampleAction> {
    private val stateMachine = createSampleStateMachine(
        sideEffectCreator = SampleSideEffectCreator(),
        reactiveEffect = SampleReactiveEffect(),
        initialState = null,
    )

    fun foo() {
        stateMachine
    }
}
