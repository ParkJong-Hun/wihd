package co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.sample

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class SampleReactiveEffect : StateMachine.ReactiveEffect<SampleState, SampleAction> {
    override suspend fun fire(targetStateMachine: StateMachine<SampleState, SampleAction>) {
        val sampleFlow: Flow<Boolean> = flow {
            while (true) {
                val dummy = UUID.randomUUID().toString().slice(0..9)
                if (dummy.contains("1")) {
                    emit(true)
                    break
                } else {
                    emit(false)
                }
                delay(1000)
            }
        }

        sampleFlow.collect { isApplicable ->
            (targetStateMachine.currentState as? SampleState.Stable.Waiting)?.let {
                if (isApplicable) targetStateMachine.dispatch(SampleAction.Apply("Succeed"))
            }
        }
    }
}
