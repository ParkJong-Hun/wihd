@file:Suppress("UnusedPrivateMember")

package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.ReactiveEffect
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.SideEffect
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachineTestCase
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachineTester
import org.junit.Test
import kotlin.test.assertEquals

internal class SampleStateMachineTest : StateMachineTester<SampleState, SampleAction> {
    override fun targetSideEffectCreator(): StateMachine.SideEffectCreator<out SideEffect<SampleState, SampleAction>, SampleState, SampleAction> {
        return SampleSideEffectCreator()
    }

    override fun targetReactiveEffect(): ReactiveEffect<SampleState, SampleAction> {
        return SampleReactiveEffect()
    }

    @Test
    fun test() {
        assertEquals("dummy", "dummy")
    }

    private fun caseNone(
        action: SampleAction,
        afterState: SampleState?,
        sideEffect: SampleSideEffect?,
    ) = StateMachineTestCase(
        beforeState = SampleState.None,
        afterState = afterState,
        action = action,
        sideEffect = sideEffect,
        creator = { initial ->
            createSampleStateMachine(
                sideEffectCreator = targetSideEffectCreator(),
                reactiveEffect = targetReactiveEffect(),
                initialState = initial
            )
        },
    )

    private fun caseLoading(
        action: SampleAction,
        afterState: SampleState?,
        sideEffect: SampleSideEffect?,
    ) = StateMachineTestCase(
        beforeState = SampleState.Loading,
        afterState = afterState,
        action = action,
        sideEffect = sideEffect,
        creator = { initial ->
            createSampleStateMachine(
                sideEffectCreator = targetSideEffectCreator(),
                reactiveEffect = targetReactiveEffect(),
                initialState = initial
            )
        },
    )

    private fun caseError(
        action: SampleAction,
        afterState: SampleState?,
        sideEffect: SampleSideEffect?,
    ) = StateMachineTestCase(
        beforeState = SampleState.Error(throwable = DUMMY_THROWABLE),
        afterState = afterState,
        action = action,
        sideEffect = sideEffect,
        creator = { initial ->
            createSampleStateMachine(
                sideEffectCreator = targetSideEffectCreator(),
                reactiveEffect = targetReactiveEffect(),
                initialState = initial
            )
        },
    )

    private fun caseStableWaiting(
        action: SampleAction,
        afterState: SampleState?,
        sideEffect: SampleSideEffect?,
    ) = StateMachineTestCase(
        beforeState = SampleState.Stable.Waiting(data = DUMMY_DATA),
        afterState = afterState,
        action = action,
        sideEffect = sideEffect,
        creator = { initial ->
            createSampleStateMachine(
                sideEffectCreator = targetSideEffectCreator(),
                reactiveEffect = targetReactiveEffect(),
                initialState = initial
            )
        },
    )

    private fun caseStableSuccess(
        action: SampleAction,
        afterState: SampleState?,
        sideEffect: SampleSideEffect?,
    ) = StateMachineTestCase(
        beforeState = SampleState.Stable.Success(data = DUMMY_DATA),
        afterState = afterState,
        action = action,
        sideEffect = sideEffect,
        creator = { initial ->
            createSampleStateMachine(
                sideEffectCreator = targetSideEffectCreator(),
                reactiveEffect = targetReactiveEffect(),
                initialState = initial
            )
        },
    )

    companion object {
        private val DUMMY_THROWABLE = Throwable("dummy")
        private const val DUMMY_DATA = "dummy"
    }
}
