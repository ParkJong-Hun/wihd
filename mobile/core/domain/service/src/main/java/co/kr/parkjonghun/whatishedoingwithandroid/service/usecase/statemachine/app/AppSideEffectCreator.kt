package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.UserRepository

internal class AppSideEffectCreator(
    private val userRepository: UserRepository,
) : StateMachine.SideEffectCreator<AppSideEffect, AppState, AppAction> {
    override fun create(state: AppState, action: AppAction): AppSideEffect? {
        return when (action) {
            is AppAction.CheckLogin -> {
                AppSideEffect.GetUser(userRepository)
            }

            is AppAction.Process -> {
                AppSideEffect.SaveToken(userRepository)
            }

            else -> null
        }
    }
}
