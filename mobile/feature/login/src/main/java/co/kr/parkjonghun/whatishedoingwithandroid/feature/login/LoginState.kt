package co.kr.parkjonghun.whatishedoingwithandroid.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.LoginAction
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.LoginState
import co.kr.parkjonghun.whatishedoingwithandroid.ui.base.UiState
import kotlinx.coroutines.flow.map
import org.koin.compose.rememberKoinInject

@Immutable
data class LoginUiState(
    val isShowLoading: Boolean,
    val error: Throwable?,
    val isLoginSuccess: Boolean,
) : UiState {
    val isShowError: Boolean = error != null
}

@Composable
fun rememberLoginUiState(): Pair<State<LoginUiState>, LoginIntent> {
    val stateMachine = rememberKoinInject<StateMachine<LoginState, LoginAction>>()
    val state = remember {
        LoginReducer(stateMachine).appUiState
    }.collectAsState(
        initial = LoginUiState(
            isShowLoading = false,
            error = null,
            isLoginSuccess = false,
        ),
    )
    val intent = remember {
        LoginIntent(stateMachine)
    }
    return state to intent
}

@Stable
class LoginReducer(
    stateMachine: StateMachine<LoginState, LoginAction>,
) {
    val appUiState = stateMachine.flow.map { domainState ->
        when (domainState) {
            is LoginState.None -> {
                LoginUiState(
                    isShowLoading = false,
                    error = null,
                    isLoginSuccess = false,
                )
            }

            is LoginState.Loading -> {
                LoginUiState(
                    isShowLoading = true,
                    error = null,
                    isLoginSuccess = false,
                )
            }

            is LoginState.Success -> {
                LoginUiState(
                    isShowLoading = false,
                    error = null,
                    isLoginSuccess = true,
                )
            }

            is LoginState.Error -> {
                LoginUiState(
                    isShowLoading = false,
                    error = null,
                    isLoginSuccess = true,
                )
            }
        }
    }
}

class LoginIntent(
    private val stateMachine: StateMachine<LoginState, LoginAction>,
) {
    fun login() {
        stateMachine.dispatch(LoginAction.HogeAction)
    }

    fun confirmErrorDialog() {
        stateMachine.dispatch(LoginAction.Resolve)
    }
}