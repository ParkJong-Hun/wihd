package co.kr.parkjonghun.whatishedoingwithandroid.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val profileScreenRoute = "profile"

fun NavGraphBuilder.profileScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
) {
    composable(profileScreenRoute) {
        ProfileScreen(
            modifier = modifier,
            contentPadding = contentPadding,
        )
    }
}

fun NavController.navigateToProfileScreen() {
    navigate(profileScreenRoute) {
        popUpTo(id = graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun ProfileScreen(
    // TODO state holder
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    ProfileBody()
}

@Composable
fun ProfileBody(

) {
    Text("profile")
}