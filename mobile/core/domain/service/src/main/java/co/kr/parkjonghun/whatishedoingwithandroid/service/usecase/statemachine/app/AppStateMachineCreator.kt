package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app

import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.createStateMachine

fun createAppStateMachineCreator(
    sideEffectCreator: StateMachine.SideEffectCreator<AppSideEffect, AppState, AppAction>,
    initialState: AppState?,
) = createStateMachine(
    name = "App",
    initialState = initialState ?: AppState.None,
    sideEffectCreator = sideEffectCreator,
) {
    fromState<AppState.None> {
        on<AppAction.CheckLogin> {
            transitionTo(AppState.Loading)
        }
        on<AppAction.Process> {
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
}
