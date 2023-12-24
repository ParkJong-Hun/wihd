package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.ValidTransition
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.mapper.doaminMode

sealed interface AppSideEffect : StateMachine.SideEffect<AppState, AppAction> {
    class SaveToken(
        private val userRepository: UserRepository,
    ) : AppSideEffect {
        override suspend fun fire(
            targetStateMachine: StateMachine<AppState, AppAction>,
            validTransition: ValidTransition<AppState, AppAction>,
        ) {
            val newToken =
                (validTransition.targetAction as AppAction.Process).tokenDto.doaminMode()
            runCatching {
                userRepository.saveTokenAndRetrieveUser(token = newToken)
            }.onSuccess { user ->
                targetStateMachine.dispatch(AppAction.AppAvailable(user))
            }.onFailure { throwable ->
                targetStateMachine.dispatch(AppAction.Fail(throwable))
            }
        }
    }

    class GetUser(
        private val userRepository: UserRepository,
    ) : AppSideEffect {
        override suspend fun fire(
            targetStateMachine: StateMachine<AppState, AppAction>,
            validTransition: ValidTransition<AppState, AppAction>,
        ) {
            runCatching {
                userRepository.getToken()?.let {
                    userRepository.getUser(it)
                }
            }
                .onSuccess { result ->
                    result?.let { user ->
                        targetStateMachine.dispatch(AppAction.AppAvailable(user))
                    } ?: let {
                        targetStateMachine.dispatch(AppAction.AppUnavailable)
                    }
                }
                .onFailure { throwable ->
                    targetStateMachine.dispatch(AppAction.Fail(throwable))
                }
        }
    }
}
