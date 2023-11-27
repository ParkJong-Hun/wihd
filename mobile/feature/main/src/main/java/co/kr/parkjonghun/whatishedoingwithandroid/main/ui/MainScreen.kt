package co.kr.parkjonghun.whatishedoingwithandroid.main.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.kr.parkjonghun.whatishedoingwithandroid.main.ui.navigation.MainNavigationRail
import co.kr.parkjonghun.whatishedoingwithandroid.main.ui.navigation.MainNavigationRailItem
import co.kr.parkjonghun.whatishedoingwithandroid.ui.extension.WihdPreview
import co.kr.parkjonghun.whatishedoingwithandroid.ui.theme.MobileTheme

const val mainScreenRoute = "main"

fun NavGraphBuilder.mainScreen(
    // TODO
) {
    composable(mainScreenRoute) {
        MainScreen()
    }
}

@Composable
fun MainScreen(
    // TODO
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    MainScreen(
        uiState = state,
        onRailItemSelected = viewModel::onRailItemSelected,
        routeToItem = viewModel::routeToItem,
    )
}

@Composable
private fun MainScreen(
    // FIXME this is temp data
    uiState: String,
    onRailItemSelected: (NavController, MainNavigationRailItem) -> Unit,
    routeToItem: String.() -> MainNavigationRailItem?,
) {
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentTab = navBackStackEntry?.destination?.route?.routeToItem()
    Row {
        MainNavigationRail(
            mainRailItems = MainNavigationRailItem.entries,
            onRailItemSelected = { item ->
                onRailItemSelected(mainNavController, item)
            },
            selectedRailItem = currentTab ?: MainNavigationRailItem.NEWS
        )
    }
}

@WihdPreview
@Composable
private fun MainScreenPreview() {
    MobileTheme {
        MainScreen(
            uiState = "test test",
            onRailItemSelected = { _, _ -> },
            routeToItem = { MainNavigationRailItem.POST },
        )
    }
}