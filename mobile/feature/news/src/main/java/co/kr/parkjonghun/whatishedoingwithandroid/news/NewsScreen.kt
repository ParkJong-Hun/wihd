package co.kr.parkjonghun.whatishedoingwithandroid.news

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    NewsBody()
}

@Composable
fun NewsBody() {
    Text("news")
}
