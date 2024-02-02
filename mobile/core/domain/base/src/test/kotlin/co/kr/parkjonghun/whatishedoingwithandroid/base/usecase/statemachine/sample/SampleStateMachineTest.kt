package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.sample

internal class SampleStateMachineTest {
    private val stateMachine = createSampleStateMachine(
        sideEffectCreator = SampleSideEffectCreator(),
        reactiveEffect = SampleReactiveEffect(),
        initialState = null,
    )
}
