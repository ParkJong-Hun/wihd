package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.Action
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation.TokenDto
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.User

sealed class AppAction : Action {
    data object CheckLogin : AppAction()

    internal data class AppAvailable(val user: User) : AppAction()

    internal data object AppUnavailable : AppAction()
    data class Process(val tokenDto: TokenDto) : AppAction()

    internal data class Fail(val throwable: Throwable) : AppAction()
    data object ResolveError : AppAction()
}
