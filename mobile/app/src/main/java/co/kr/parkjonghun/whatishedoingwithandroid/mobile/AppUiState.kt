package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import co.kr.parkjonghun.whatishedoingwithandroid.base.usecase.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation.TokenDto
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app.AppAction
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.statemachine.app.AppState
import co.kr.parkjonghun.whatishedoingwithandroid.ui.base.Intent
import co.kr.parkjonghun.whatishedoingwithandroid.ui.base.Processor
import co.kr.parkjonghun.whatishedoingwithandroid.ui.base.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

@Composable
fun rememberAppProcessor(): Processor<AppUiState, AppIntent> {
    val scope = rememberCoroutineScope()
    val stateMachine = koinInject<StateMachine<AppState, AppAction>>(
        qualifier = named("App"),
    )
    val state = remember { MutableStateFlow(AppUiState()) }
    val intent = remember { AppIntent(stateMachine) }
    remember { AppReducer(stateMachine, state, scope) }
    return state.collectAsState() to intent
}

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

class AppIntent(
    private val stateMachine: StateMachine<AppState, AppAction>,
) : Intent {
    fun init() {
        checkUser()
    }

    fun keepToken(tokenDto: TokenDto) {
        stateMachine.dispatch(AppAction.Process(tokenDto))
    }

    private fun checkUser() {
        stateMachine.dispatch(AppAction.CheckLogin)
    }
}

@Stable
class AppReducer(
    stateMachine: StateMachine<AppState, AppAction>,
    private val uiState: MutableStateFlow<AppUiState>,
    coroutineScope: CoroutineScope,
) {
    init {
        stateMachine.flow
            .onEach { domainState ->
                when (domainState) {
                    is AppState.None -> {
                        uiState.update {
                            it.copy(
                                isShowInitScreen = true,
                                isShowLoading = false,
                                isShowLoginScreen = false,
                                error = null,
                            )
                        }
                    }

                    is AppState.Loading -> {
                        uiState.update {
                            it.copy(
                                isShowInitScreen = true,
                                isShowLoading = true,
                                isShowLoginScreen = false,
                                error = null,
                            )
                        }
                    }

                    is AppState.LoggedIn -> {
                        uiState.update {
                            it.copy(
                                isShowInitScreen = false,
                                isShowLoading = false,
                                isShowLoginScreen = false,
                                error = null,
                            )
                        }
                    }

                    is AppState.NotLoggedIn -> {
                        uiState.update {
                            it.copy(
                                isShowInitScreen = false,
                                isShowLoading = false,
                                isShowLoginScreen = true,
                                error = null,
                            )
                        }
                    }

                    is AppState.Error -> {
                        uiState.update {
                            it.copy(
                                isShowInitScreen = true,
                                isShowLoading = false,
                                error = domainState.throwable,
                                isShowLoginScreen = false,
                            )
                        }
                    }
                }
            }
            .launchIn(coroutineScope)
    }
}
