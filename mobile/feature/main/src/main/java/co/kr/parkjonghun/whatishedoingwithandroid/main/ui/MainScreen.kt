package co.kr.parkjonghun.whatishedoingwithandroid.main.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting("Android")
    }
}

@WihdPreview
@Composable
private fun MainScreenPreview() {
    MobileTheme {
        MainScreen()
    }
}