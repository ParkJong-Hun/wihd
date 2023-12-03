package co.kr.parkjonghun.whatishedoingwithandroid.ui.extension

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "lightMode",
    group = "lightMode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = " dartMode",
    group = "darkMode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class WihdPreview
