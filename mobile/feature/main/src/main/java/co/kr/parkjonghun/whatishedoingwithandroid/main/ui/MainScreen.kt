@file:JvmName("MainStateKt")

package co.kr.parkjonghun.whatishedoingwithandroid.main.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.kr.parkjonghun.whatishedoingwithandroid.main.ui.navigation.MainDestination
import co.kr.parkjonghun.whatishedoingwithandroid.main.ui.navigation.MainNavigationRail
import co.kr.parkjonghun.whatishedoingwithandroid.ui.extension.WihdPreview
import co.kr.parkjonghun.whatishedoingwithandroid.ui.theme.MobileTheme

const val mainScreenRoute = "main"

fun NavGraphBuilder.mainScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavController,
) {
    composable(mainScreenRoute) {
        MainScreen(
            windowSizeClass = windowSizeClass
        )
        // TODO Maybe dialog? or bottomSheet?
    }
}

@Composable
fun MainScreen(
    windowSizeClass: WindowSizeClass,
    mainState: MainState = rememberMainState(windowSizeClass = windowSizeClass),
) {
    val currentMainDestination: MainDestination =
        mainState.navController.currentBackStackEntryAsState().value
            ?.destination
            ?.let { mainState.routeToDestination(route = it.route) }
            ?: MainDestination.NEWS

    MainBody(
        shouldShowNavRail = mainState.shouldShowNavRail,
        shouldShowBottomBar = mainState.shouldShowBottomBar,
        navigateToMain = mainState::navigateToMain,
        currentMainDestination = currentMainDestination,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainBody(
    shouldShowNavRail: Boolean,
    shouldShowBottomBar: Boolean,
    navigateToMain: (MainDestination) -> Unit,
    currentMainDestination: MainDestination,
) {
    val mainNavController = rememberNavController()
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(visible = shouldShowNavRail) {
            MainNavigationRail(
                mainRailItems = MainDestination.entries,
                onRailItemSelected = navigateToMain,
                selectedRailItem = currentMainDestination,
            )
        }
        Scaffold(
            bottomBar = {
                AnimatedVisibility(visible = shouldShowBottomBar) {
                    // TODO bottomBar
                }
            },
        ) {
            NavHost(
                navController = mainNavController,
                startDestination = "news",
                modifier = Modifier,
            ) {
                // TODO
            }
        }
    }
}

@WihdPreview
@Composable
private fun MainScreenPreview() {
    MobileTheme {
        MainBody(
            shouldShowNavRail = false,
            shouldShowBottomBar = true,
            navigateToMain = {},
            currentMainDestination = MainDestination.NEWS,
        )
    }
}