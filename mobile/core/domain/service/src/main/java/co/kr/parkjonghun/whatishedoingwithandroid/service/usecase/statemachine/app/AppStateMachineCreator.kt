package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.ReactiveEffect
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.createStateMachine

fun createAppStateMachine(
    sideEffectCreator: StateMachine.SideEffectCreator<AppSideEffect, AppState, AppAction>,
    reactiveEffect: ReactiveEffect<AppState, AppAction>,
    initialState: AppState?,
) = createStateMachine(
    name = "App",
    initialState = initialState ?: AppState.None,
    sideEffectCreator = sideEffectCreator,
    reactiveEffect = reactiveEffect,
) {
    fromState<AppState.None> {
        on<AppAction.CheckLogin> {
            transitionTo(AppState.Loading)
        }
    }

    fromState<AppState.Loading> {
        on<AppAction.AppAvailable> {
            transitionTo(AppState.LoggedIn(it.user))
        }
        on<AppAction.AppUnavailable> {
            transitionTo(AppState.NotLoggedIn)
        }
        on<AppAction.Fail> {
            transitionTo(AppState.Error(it.throwable))
        }
    }

    fromState<AppState.Error> {
        on<AppAction.ResolveError> {
            transitionTo(AppState.None)
        }
    }

    fromState<AppState.NotLoggedIn> {
        on<AppAction.AppAvailable> {
            transitionTo(AppState.LoggedIn(it.user))
        }
    }
}
