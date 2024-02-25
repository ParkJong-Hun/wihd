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
class TextScreenshotTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test_WihdText_default() {
        composeTestRule.captureMultiTheme("Text") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.Default,
                )
            }
        }
    }

    @Test
    fun test_WihdText_d1() {
        composeTestRule.captureMultiTheme("Text", "D1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.D1,
                )
            }
        }
    }

    @Test
    fun test_WihdText_d2() {
        composeTestRule.captureMultiTheme("Text", "D2") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.D2,
                )
            }
        }
    }

    @Test
    fun test_WihdText_d3() {
        composeTestRule.captureMultiTheme("Text", "D3") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.D3,
                )
            }
        }
    }

    @Test
    fun test_WihdText_h1() {
        composeTestRule.captureMultiTheme("Text", "H1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.H1,
                )
            }
        }
    }

    @Test
    fun test_WihdText_h2() {
        composeTestRule.captureMultiTheme("Text", "H2") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.H2,
                )
            }
        }
    }

    @Test
    fun test_WihdText_h3() {
        composeTestRule.captureMultiTheme("Text", "H3") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.H3,
                )
            }
        }
    }

    @Test
    fun test_WihdText_t1() {
        composeTestRule.captureMultiTheme("Text", "T1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.T1,
                )
            }
        }
    }

    @Test
    fun test_WihdText_t2() {
        composeTestRule.captureMultiTheme("Text", "T1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.T2,
                )
            }
        }
    }

    @Test
    fun test_WihdText_t3() {
        composeTestRule.captureMultiTheme("Text", "T1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.T3,
                )
            }
        }
    }

    @Test
    fun test_WihdText_b1() {
        composeTestRule.captureMultiTheme("Text", "T1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.B1,
                )
            }
        }
    }

    @Test
    fun test_WihdText_b2() {
        composeTestRule.captureMultiTheme("Text", "T1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.B2,
                )
            }
        }
    }

    @Test
    fun test_WihdText_b3() {
        composeTestRule.captureMultiTheme("Text", "T1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.B3,
                )
            }
        }
    }

    @Test
    fun test_WihdText_l1() {
        composeTestRule.captureMultiTheme("Text", "T1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.L1,
                )
            }
        }
    }

    @Test
    fun test_WihdText_l2() {
        composeTestRule.captureMultiTheme("Text", "T1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.L2,
                )
            }
        }
    }

    @Test
    fun test_WihdText_l3() {
        composeTestRule.captureMultiTheme("Text", "T1") { description ->
            Surface {
                WihdText(
                    text = "$description Text\nThis is screenshot test.",
                    style = WihdTextStyle.L3,
                )
            }
        }
    }
}
