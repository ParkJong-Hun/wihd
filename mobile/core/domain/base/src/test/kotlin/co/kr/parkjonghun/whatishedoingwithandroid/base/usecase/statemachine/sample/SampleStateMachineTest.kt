@file:Suppress("UnusedPrivateMember")

package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.ReactiveEffect
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.SideEffect
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachineTestCase
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachineTester
import org.junit.Test

internal class SampleStateMachineTest : StateMachineTester<SampleState, SampleAction> {
    override fun targetSideEffectCreator(): StateMachine.SideEffectCreator<out SideEffect<SampleState, SampleAction>, SampleState, SampleAction> {
        return SampleSideEffectCreator()
    }

    override fun targetReactiveEffect(): ReactiveEffect<SampleState, SampleAction> {
        return SampleReactiveEffect()
    }

    @Test
    fun test_caseNone() {
        listOf(
            caseNone(
                action = SampleAction.Do,
                afterState = SampleState.Loading,
                sideEffect = SampleSideEffect(),
            ),
            caseNone(
                action = SampleAction.ResolveError,
            ),
            caseNone(
                action = SampleAction.Retry,
            ),
            caseNone(
                action = SampleAction.Fail(DUMMY_THROWABLE),
            ),
            caseNone(
                action = SampleAction.Succeed(DUMMY_DATA),
            ),
            caseNone(
                action = SampleAction.Apply(DUMMY_DATA),
            ),
        ).forEach {
            testDispatch(
                action = it.action,
                beforeState = it.beforeState,
                afterState = it.afterState,
                sideEffect = it.sideEffect,
                targetStateMachine = it.creator(it.beforeState),
            )
        }
    }

    @Test
    fun test_caseLoading() {
        listOf(
            caseLoading(
                action = SampleAction.Do,
            ),
            caseLoading(
                action = SampleAction.ResolveError,
            ),
            caseLoading(
                action = SampleAction.Retry,
            ),
            caseLoading(
                action = SampleAction.Fail(DUMMY_THROWABLE),
                afterState = SampleState.Error(throwable = DUMMY_THROWABLE),
            ),
            caseLoading(
                action = SampleAction.Succeed(DUMMY_DATA),
                afterState = SampleState.Stable.Waiting(data = DUMMY_DATA),
            ),
            caseLoading(
                action = SampleAction.Apply(DUMMY_DATA),
            ),
        ).forEach {
            testDispatch(
                action = it.action,
                beforeState = it.beforeState,
                afterState = it.afterState,
                sideEffect = it.sideEffect,
                targetStateMachine = it.creator(it.beforeState),
            )
        }
    }

    @Test
    fun test_caseError() {
        listOf(
            caseError(
                action = SampleAction.Do,
            ),
            caseError(
                action = SampleAction.ResolveError,
                afterState = SampleState.None,
            ),
            caseError(
                action = SampleAction.Retry,
                afterState = SampleState.Loading,
                sideEffect = SampleSideEffect(),
            ),
            caseError(
                action = SampleAction.Fail(DUMMY_THROWABLE),
            ),
            caseError(
                action = SampleAction.Succeed(DUMMY_DATA),
            ),
            caseError(
                action = SampleAction.Apply(DUMMY_DATA),
            ),
        ).forEach {
            testDispatch(
                action = it.action,
                beforeState = it.beforeState,
                afterState = it.afterState,
                sideEffect = it.sideEffect,
                targetStateMachine = it.creator(it.beforeState),
            )
        }
    }

    @Test
    fun test_caseStableWaiting() {
        listOf(
            caseStableWaiting(
                action = SampleAction.Do,
            ),
            caseStableWaiting(
                action = SampleAction.ResolveError,
            ),
            caseStableWaiting(
                action = SampleAction.Retry,
            ),
            caseStableWaiting(
                action = SampleAction.Fail(DUMMY_THROWABLE),
            ),
            caseStableWaiting(
                action = SampleAction.Succeed(DUMMY_DATA),
            ),
            caseStableWaiting(
                action = SampleAction.Apply(DUMMY_DATA),
                afterState = SampleState.Stable.Success(data = DUMMY_DATA),
                sideEffect = SampleSideEffect(),
            ),
        ).forEach {
            testDispatch(
                action = it.action,
                beforeState = it.beforeState,
                afterState = it.afterState,
                sideEffect = it.sideEffect,
                targetStateMachine = it.creator(it.beforeState),
            )
        }
    }

    @Test
    fun test_caseStableSuccess() {
        listOf(
            caseStableSuccess(
                action = SampleAction.Do,
            ),
            caseStableSuccess(
                action = SampleAction.ResolveError,
            ),
            caseStableSuccess(
                action = SampleAction.Retry,
            ),
            caseStableSuccess(
                action = SampleAction.Fail(DUMMY_THROWABLE),
            ),
            caseStableSuccess(
                action = SampleAction.Succeed(DUMMY_DATA),
            ),
            caseStableSuccess(
                action = SampleAction.Apply(DUMMY_DATA),
            ),
        ).forEach {
            testDispatch(
                action = it.action,
                beforeState = it.beforeState,
                afterState = it.afterState,
                sideEffect = it.sideEffect,
                targetStateMachine = it.creator(it.beforeState),
            )
        }
    }

    private fun caseNone(
        action: SampleAction,
        afterState: SampleState? = null,
        sideEffect: SampleSideEffect? = null,
    ) = StateMachineTestCase(
        beforeState = SampleState.None,
        afterState = afterState,
        action = action,
        sideEffect = sideEffect,
        creator = { initial ->
            createSampleStateMachine(
                sideEffectCreator = targetSideEffectCreator(),
                reactiveEffect = targetReactiveEffect(),
                initialState = initial,
                coroutineContext = testCoroutineContext(),
            )
        },
    )

    private fun caseLoading(
        action: SampleAction,
        afterState: SampleState? = null,
        sideEffect: SampleSideEffect? = null,
    ) = StateMachineTestCase(
        beforeState = SampleState.Loading,
        afterState = afterState,
        action = action,
        sideEffect = sideEffect,
        creator = { initial ->
            createSampleStateMachine(
                sideEffectCreator = targetSideEffectCreator(),
                reactiveEffect = targetReactiveEffect(),
                initialState = initial,
                coroutineContext = testCoroutineContext(),
            )
        },
    )

    private fun caseError(
        action: SampleAction,
        afterState: SampleState? = null,
        sideEffect: SampleSideEffect? = null,
    ) = StateMachineTestCase(
        beforeState = SampleState.Error(throwable = DUMMY_THROWABLE),
        afterState = afterState,
        action = action,
        sideEffect = sideEffect,
        creator = { initial ->
            createSampleStateMachine(
                sideEffectCreator = targetSideEffectCreator(),
                reactiveEffect = targetReactiveEffect(),
                initialState = initial,
                coroutineContext = testCoroutineContext(),
            )
        },
    )

    private fun caseStableWaiting(
        action: SampleAction,
        afterState: SampleState? = null,
        sideEffect: SampleSideEffect? = null,
    ) = StateMachineTestCase(
        beforeState = SampleState.Stable.Waiting(data = DUMMY_DATA),
        afterState = afterState,
        action = action,
        sideEffect = sideEffect,
        creator = { initial ->
            createSampleStateMachine(
                sideEffectCreator = targetSideEffectCreator(),
                reactiveEffect = targetReactiveEffect(),
                initialState = initial,
                coroutineContext = testCoroutineContext(),
            )
        },
    )

    private fun caseStableSuccess(
        action: SampleAction,
        afterState: SampleState? = null,
        sideEffect: SampleSideEffect? = null,
    ) = StateMachineTestCase(
        beforeState = SampleState.Stable.Success(data = DUMMY_DATA),
        afterState = afterState,
        action = action,
        sideEffect = sideEffect,
        creator = { initial ->
            createSampleStateMachine(
                sideEffectCreator = targetSideEffectCreator(),
                reactiveEffect = targetReactiveEffect(),
                initialState = initial,
                coroutineContext = testCoroutineContext(),
            )
        },
    )

    companion object {
        private val DUMMY_THROWABLE = Throwable("dummy")
        private const val DUMMY_DATA = "Succeed"
    }
}
