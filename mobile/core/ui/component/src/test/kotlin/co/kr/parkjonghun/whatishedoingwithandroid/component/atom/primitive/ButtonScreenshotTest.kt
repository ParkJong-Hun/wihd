package co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive

import androidx.activity.ComponentActivity
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
class ButtonScreenshotTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test_PrimaryFilledButton() {
        composeTestRule.captureMultiTheme("Button", "Primary") { description ->
            Surface {
                PrimaryFilledButton(
                    onClick = {},
                ) {
                    Text("$description Button")
                }
            }
        }
    }

    @Test
    fun test_SecondaryFilledButton() {
        composeTestRule.captureMultiTheme("Button", "Second") { description ->
            Surface {
                SecondaryFilledButton(
                    onClick = {},
                ) {
                    Text("$description Button")
                }
            }
        }
    }

    @Test
    fun test_TertiaryFilledButton() {
        composeTestRule.captureMultiTheme("Button", "Tertiary") { description ->
            Surface {
                TertiaryFilledButton(
                    onClick = {},
                ) {
                    Text("$description Button")
                }
            }
        }
    }

    @Test
    fun test_LinkButton() {
        composeTestRule.captureMultiTheme("Button", "Link") { description ->
            Surface {
                LinkButton(
                    onClick = {},
                ) {
                    Text("$description Button")
                }
            }
        }
    }
}
