package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app.AppAction
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app.AppState
import co.kr.parkjonghun.whatishedoingwithandroid.ui.base.Intent
import co.kr.parkjonghun.whatishedoingwithandroid.ui.base.UiState
import kotlinx.coroutines.flow.map
import org.koin.compose.rememberKoinInject

@Immutable
data class AppUiState(
    val isShowInitScreen: Boolean = true,
    val isShowLoading: Boolean = false,
    val isShowLoginScreen: Boolean = false,
    val error: Throwable? = null,
) : UiState {
    val isShowError: Boolean = error != null
    val isShowTop: Boolean = !isShowLoginScreen && !isShowInitScreen
}

@Composable
fun rememberAppUiState(): Pair<State<AppUiState>, AppIntent> {
    val stateMachine = rememberKoinInject<StateMachine<AppState, AppAction>>()
    val state = remember {
        AppReducer(stateMachine).appUiState
    }.collectAsState(
        initial = AppUiState(),
    )
    val intent = remember {
        AppIntent(stateMachine)
    }
    return state to intent
}

@Stable
class AppReducer(
    stateMachine: StateMachine<AppState, AppAction>,
) {
    val appUiState = stateMachine.flow.map { domainState ->
        when (domainState) {
            is AppState.None -> {
                AppUiState(
                    isShowInitScreen = true,
                    isShowLoading = false,
                    isShowLoginScreen = false,
                    error = null,
                )
            }

            is AppState.Loading -> {
                AppUiState(
                    isShowInitScreen = true,
                    isShowLoading = true,
                    isShowLoginScreen = false,
                    error = null,
                )
            }

            is AppState.LoggedIn -> {
                AppUiState(
                    isShowInitScreen = false,
                    isShowLoading = false,
                    isShowLoginScreen = false,
                    error = null,
                )
            }

            is AppState.NotLoggedIn -> {
                AppUiState(
                    isShowInitScreen = false,
                    isShowLoading = false,
                    isShowLoginScreen = true,
                    error = null,
                )
            }

            is AppState.Error -> {
                AppUiState(
                    isShowInitScreen = true,
                    isShowLoading = false,
                    error = domainState.throwable,
                    isShowLoginScreen = false,
                )
            }
        }
    }
}

class AppIntent(
    private val stateMachine: StateMachine<AppState, AppAction>,
) : Intent {
    fun init() {
        checkUser()
    }

    fun loginSuccess() {
        checkUser()
    }

    private fun checkUser() {
        stateMachine.dispatch(AppAction.CheckLogin)
    }
}