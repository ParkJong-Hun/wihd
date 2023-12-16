package co.kr.parkjonghun.whatishedoingwithandroid.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val newsScreenRoute = "news"

fun NavGraphBuilder.newsScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
) {
    composable(newsScreenRoute) {
        NewsScreen(
            modifier = modifier,
            contentPadding = contentPadding,
        )
    }
}

fun NavController.navigateToNewsScreen() {
    navigate(newsScreenRoute) {
        popUpTo(id = graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Suppress("UnusedParameter")
@Composable
fun NewsScreen(
    // TODO state holder
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    val (state, intent) = rememberNewsUiState()

    NewsBody(
        isShowLoading = state.value.isShowLoading,
        isShowError = state.value.isShowError to state.value.error,
        isShowNewsfeed = state.value.isShowSomething,
        onLoginClick = intent::login,
        onErrorOkClick = intent::confirmErrorDialog,
        modifier = modifier,
    )
}

@Composable
fun NewsBody(
    isShowLoading: Boolean,
    isShowError: Pair<Boolean, Throwable?>,
    isShowNewsfeed: Boolean,
    onLoginClick: () -> Unit,
    onErrorOkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        if (isShowNewsfeed) {
            Row(
                modifier = modifier,
            ) {
                Text("News")
                Spacer(modifier = Modifier.height(20.dp))
                Text("hoge news")
                Text("fuga news")
            }
        } else {
            Row(
                modifier = modifier,
            ) {
                Text("News")
                Button(onClick = onLoginClick) {
                    Text(text = "OAuth Login")
                }
            }
        }

        if (isShowLoading) {
            // TODO Show animation.
        }

        if (isShowError.first) {
            Dialog(onDismissRequest = onErrorOkClick) {
                Text("error occurs\n${isShowError.second}")
            }
        }
    }
}
