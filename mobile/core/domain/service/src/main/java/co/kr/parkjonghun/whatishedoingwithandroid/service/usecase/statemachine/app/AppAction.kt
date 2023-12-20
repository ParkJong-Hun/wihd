package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.Action
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.sample.User

sealed class AppAction : Action {
    data object CheckLogin : AppAction()
    data class AppAvailable(val user: User) : AppAction()
    data object AppUnavailable : AppAction()
    data class Fail(val throwable: Throwable) : AppAction()
    data object ResolveError : AppAction()
}