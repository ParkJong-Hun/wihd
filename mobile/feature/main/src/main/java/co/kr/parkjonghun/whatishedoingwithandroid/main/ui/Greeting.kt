package co.kr.parkjonghun.whatishedoingwithandroid.main.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.kr.parkjonghun.whatishedoingwithandroid.ui.theme.MobileTheme

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileTheme {
        Greeting("Android")
    }
}