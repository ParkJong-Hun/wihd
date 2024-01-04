package co.kr.parkjonghun.whatishedoingwithandroid.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.LoginAction
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.login.LoginState
import co.kr.parkjonghun.whatishedoingwithandroid.system.base.Processor
import co.kr.parkjonghun.whatishedoingwithandroid.system.base.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

@Composable
fun rememberLoginProcessor(): Processor<LoginUiState, LoginIntent> {
    val scope = rememberCoroutineScope()
    val stateMachine = koinInject<StateMachine<LoginState, LoginAction>>(
        qualifier = named("Login"),
    )
    val state = remember { MutableStateFlow(LoginUiState()) }
    val intent = remember { LoginIntent(stateMachine) }
    remember { LoginReducer(stateMachine, state, scope) }
    return state.collectAsState().value to intent
}

@Immutable
data class LoginUiState(
    val isShowLoading: Boolean = false,
    val error: Throwable? = null,
) : UiState {
    val isShowError: Boolean = error != null
}

class LoginIntent(
    private val stateMachine: StateMachine<LoginState, LoginAction>,
) {
    fun login() {
        stateMachine.dispatch(LoginAction.Login)
    }

    fun confirmErrorDialog() {
        stateMachine.dispatch(LoginAction.Resolve)
    }
}

@Stable
class LoginReducer(
    stateMachine: StateMachine<LoginState, LoginAction>,
    private val uiState: MutableStateFlow<LoginUiState>,
    coroutineScope: CoroutineScope,
) {
    init {
        stateMachine.flow
            .onEach { domainState ->
                when (domainState) {
                    is LoginState.None -> {
                        uiState.update {
                            it.copy(
                                isShowLoading = false,
                                error = null,
                            )
                        }
                    }

                    is LoginState.Loading -> {
                        uiState.update {
                            it.copy(
                                isShowLoading = true,
                                error = null,
                            )
                        }
                    }

                    is LoginState.Success -> {
                        uiState.update {
                            it.copy(
                                isShowLoading = false,
                                error = null,
                            )
                        }
                    }

                    is LoginState.Error -> {
                        uiState.update {
                            it.copy(
                                isShowLoading = false,
                                error = null,
                            )
                        }
                    }
                }
            }
            .launchIn(coroutineScope)
    }
}
