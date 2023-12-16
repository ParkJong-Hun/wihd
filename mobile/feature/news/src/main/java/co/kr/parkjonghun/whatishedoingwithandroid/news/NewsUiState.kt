package co.kr.parkjonghun.whatishedoingwithandroid.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine.StateMachine
import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample.SampleAction
import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.sample.SampleState
import co.kr.parkjonghun.whatishedoingwithandroid.ui.base.Intent
import co.kr.parkjonghun.whatishedoingwithandroid.ui.base.UiState
import kotlinx.coroutines.flow.map
import org.koin.compose.rememberKoinInject

@Immutable
data class NewsUiState(
    val isShowLoading: Boolean,
    val error: Throwable?,
    val isShowSomething: Boolean,
) : UiState {
    val isShowError: Boolean = error != null
}

@Composable
fun rememberNewsUiState(): Pair<State<NewsUiState>, NewsIntent> {
    val stateMachine = rememberKoinInject<StateMachine<SampleState, SampleAction>>()
    val state = remember {
        NewsReducer(stateMachine).newsUiState
    }.collectAsState(
        initial = NewsUiState(
            isShowLoading = false,
            error = null,
            isShowSomething = false,
        ),
    )
    val intent = remember {
        NewsIntent(stateMachine)
    }
    return state to intent
}

@Stable
class NewsReducer(
    stateMachine: StateMachine<SampleState, SampleAction>,
) {
    val newsUiState = stateMachine.flow.map { domainState ->
        when (domainState) {
            is SampleState.None -> {
                NewsUiState(
                    isShowLoading = false,
                    error = null,
                    isShowSomething = false,
                )
            }

            is SampleState.Loading -> {
                NewsUiState(
                    isShowLoading = true,
                    error = null,
                    isShowSomething = false,
                )
            }

            is SampleState.Success -> {
                NewsUiState(
                    isShowLoading = false,
                    error = null,
                    isShowSomething = true,
                )
            }

            is SampleState.Error -> {
                NewsUiState(
                    isShowLoading = false,
                    error = null,
                    isShowSomething = true,
                )
            }
        }
    }
}

class NewsIntent(
    private val stateMachine: StateMachine<SampleState, SampleAction>,
) : Intent {
    fun login() {
        stateMachine.dispatch(SampleAction.HogeAction)
    }

    fun confirmErrorDialog() {
        stateMachine.dispatch(SampleAction.Resolve)
    }
}
