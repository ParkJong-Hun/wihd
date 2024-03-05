package co.kr.parkjonghun.whatishedoingwithandroid.post

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.WihdText
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.WihdTextStyle
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.analytics.screen.trackScreen

const val postScreenRoute = "post"

fun NavGraphBuilder.postScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
) {
    composable(postScreenRoute) {
        PostScreen(
            modifier = modifier,
            contentPadding = contentPadding,
        )
    }
}

fun NavController.navigateToPostScreen() {
    navigate(postScreenRoute) {
        popUpTo(id = graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Suppress("UnusedParameter")
@Composable
fun PostScreen(
    // TODO state holder
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    trackScreen(name = "PostScreen")

    PostBody()
}

@Composable
fun PostBody() {
    WihdText(
        text = "post",
        style = WihdTextStyle.H1,
    )
}
