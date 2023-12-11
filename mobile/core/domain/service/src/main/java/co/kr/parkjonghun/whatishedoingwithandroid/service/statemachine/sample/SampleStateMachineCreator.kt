package co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.createStateMachine

fun createSampleStateMachine(
    sideEffectCreator: StateMachine.SideEffectCreator<SampleSideEffect, SampleState, SampleAction>,
    initialState: SampleState?,
) = createStateMachine(
    name = "Sample",
    initialState = initialState ?: SampleState.None,
    sideEffectCreator = sideEffectCreator,
) {
    fromState<SampleState.None> {
        on<SampleAction.HogeAction> {
            transitionTo(SampleState.Loading)
        }
    }

    fromState<SampleState.Loading> {
        on<SampleAction.Succeed> {
            transitionTo(SampleState.Success(it.token))
        }
        on<SampleAction.Fail> {
            transitionTo(SampleState.Error(it.throwable))
        }
    }

    fromState<SampleState.Error> {
        on<SampleAction.Resolve> {
            transitionTo(SampleState.None)
        }
    }
}
