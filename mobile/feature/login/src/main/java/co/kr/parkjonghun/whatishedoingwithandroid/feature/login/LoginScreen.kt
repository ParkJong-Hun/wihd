package co.kr.parkjonghun.whatishedoingwithandroid.feature.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog

@Composable
@Suppress("UnusedParameter")
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (state, intent) = rememberLoginUiState()

    LaunchedEffect(state.value.isLoginSuccess) {
        onLoginSuccess()
    }

    LoginBody(
        isShowError = state.value.isShowError to state.value.error,
        onClickLogin = intent::login,
        onClickErrorOk = intent::confirmErrorDialog,
        modifier = modifier,
    )
}

@Composable
private fun LoginBody(
    isShowError: Pair<Boolean, Throwable?>,
    onClickLogin: () -> Unit,
    onClickErrorOk: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        Row(
            modifier = modifier,
        ) {
            Button(onClick = onClickLogin) {
                Text("Login")
            }
        }
        // TODO handle error
        if (isShowError.first) {
            Dialog(onDismissRequest = onClickErrorOk) {
                Text("error occurs\n${isShowError.second}")
            }
        }
    }
}
