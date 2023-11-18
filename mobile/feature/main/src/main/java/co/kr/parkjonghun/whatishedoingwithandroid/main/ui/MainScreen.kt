package co.kr.parkjonghun.whatishedoingwithandroid.main.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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

    LaunchedEffect(Unit) {
        Log.d("main", "uiState : $state")
    }

    MainScreen(uiState = state)
}

@Composable
private fun MainScreen(
    // FIXME thsi is temp data
    uiState: String
) {

    Greeting(
        name = uiState,
        modifier = Modifier.fillMaxSize(),
    )
}

@WihdPreview
@Composable
private fun MainScreenPreview() {
    MobileTheme {
        MainScreen()
    }
}