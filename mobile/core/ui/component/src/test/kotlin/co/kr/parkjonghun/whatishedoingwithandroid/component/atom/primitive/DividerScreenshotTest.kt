package co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive

import androidx.activity.ComponentActivity
import androidx.compose.material3.Surface
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
class DividerScreenshotTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test_ThickDivider() {
        composeTestRule.captureMultiTheme("Divider", "Thick") { _ ->
            Surface {
                ThickDivider()
            }
        }
    }
}