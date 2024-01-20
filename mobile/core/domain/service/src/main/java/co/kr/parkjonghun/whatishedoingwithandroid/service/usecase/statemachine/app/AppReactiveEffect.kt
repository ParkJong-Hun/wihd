package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.ReactiveEffect
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

class AppReactiveEffect(
    private val userRepository: UserRepository,
) : ReactiveEffect<AppState, AppAction> {
    override suspend fun fire(targetStateMachine: StateMachine<AppState, AppAction>) {
        userRepository.currentUser
            .filter {
                it != null
            }
            .distinctUntilChanged()
            .collect {
                val loggedInUser = requireNotNull(it)
                targetStateMachine.dispatch(AppAction.AppAvailable(loggedInUser))
            }
    }
}
