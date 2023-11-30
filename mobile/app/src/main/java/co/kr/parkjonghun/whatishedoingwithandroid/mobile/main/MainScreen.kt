package co.kr.parkjonghun.whatishedoingwithandroid.mobile.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
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
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation.MainBottomBar
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation.MainDestination
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation.MainNavigationRail

const val mainScreenRoute = "main"

fun NavGraphBuilder.mainScreen(
    windowSizeClass: WindowSizeClass,
    mainNestedNavGraph: NavGraphBuilder.(mainNestedNavController: NavController, PaddingValues) -> Unit,
) {
    composable(mainScreenRoute) {
        MainScreen(
            windowSizeClass = windowSizeClass,
            mainNestedNavGraph = mainNestedNavGraph,
        )
        // TODO Maybe dialog? or bottomSheet?
    }
}

@Composable
fun MainScreen(
    windowSizeClass: WindowSizeClass,
    mainNestedNavGraph: NavGraphBuilder.(NavController, PaddingValues) -> Unit,
    mainState: MainState = rememberMainState(windowSizeClass = windowSizeClass),
) {
    val currentMainDestination: MainDestination =
        mainState.navController.currentBackStackEntryAsState().value
            ?.destination
            ?.let { mainState.routeToDestination(route = it.route) }
            ?: MainDestination.NEWS

    MainBody(
        currentMainDestination = currentMainDestination,
        shouldShowNavRail = mainState.shouldShowNavRail,
        shouldShowBottomBar = mainState.shouldShowBottomBar,
        navigateToMain = mainState::navigateToMain,
        mainNestedNavGraph = mainNestedNavGraph,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainBody(
    currentMainDestination: MainDestination,
    shouldShowNavRail: Boolean,
    shouldShowBottomBar: Boolean,
    navigateToMain: (MainDestination) -> Unit,
    mainNestedNavGraph: NavGraphBuilder.(NavController, PaddingValues) -> Unit,
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
                    MainBottomBar(
                        mainTabs = MainDestination.entries,
                        onRailItemSelected = navigateToMain,
                        selectedRailItem = currentMainDestination,
                    )
                }
            },
        ) { padding ->
            NavHost(
                navController = mainNavController,
                startDestination = "news",
                modifier = Modifier,
            ) {
                mainNestedNavGraph(mainNavController, padding)
            }
        }
    }
}