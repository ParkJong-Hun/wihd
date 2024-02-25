package co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.testing.captureMultiTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.LooperMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@LooperMode(LooperMode.Mode.PAUSED)
class ImageScreenshotTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test_WihdImage() {
        composeTestRule.captureMultiTheme("Image") { description ->
            Surface {
                WihdImage(
                    painter = rememberVectorPainter(image = Icons.Default.DateRange),
                    contentDescription = "$description Image",
                    modifier = Modifier.size(100.dp),
                )
            }
        }
    }
}
