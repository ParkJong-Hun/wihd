package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.sample

import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
internal class StateMachineTest {
    private val stateMachine = createSampleStateMachine(
        sideEffectCreator = SampleSideEffectCreator(),
        reactiveEffect = SampleReactiveEffect(),
        initialState = null,
    )
}
