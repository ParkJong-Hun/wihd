package co.kr.parkjonghun.whatishedoingwithandroid.feature.login

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@Suppress("UnusedParameter")
fun LoginScreen(
    onClickLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LoginBody(
        onClickLogin = onClickLogin,
        modifier = modifier,
    )
}

@Composable
private fun LoginBody(
    onClickLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Button(onClick = onClickLogin) {
            Text("Login")
        }
    }
}
