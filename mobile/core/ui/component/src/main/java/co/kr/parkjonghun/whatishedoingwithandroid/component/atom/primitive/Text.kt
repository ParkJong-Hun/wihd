package co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import co.kr.parkjonghun.whatishedoingwithandroid.system.extension.clickableWithoutRipple
import co.kr.parkjonghun.whatishedoingwithandroid.system.theme.dark_linkBlue
import co.kr.parkjonghun.whatishedoingwithandroid.system.theme.light_linkBlue

@Composable
fun WihdText(
    text: String,
    style: WihdTextStyle,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontFamily: FontFamily? = null,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontStyle = fontStyle,
        fontFamily = fontFamily,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = when (style) {
            WihdTextStyle.Default -> LocalTextStyle.current
            WihdTextStyle.D1 -> MaterialTheme.typography.displayLarge
            WihdTextStyle.D2 -> MaterialTheme.typography.displayMedium
            WihdTextStyle.D3 -> MaterialTheme.typography.displaySmall
            WihdTextStyle.H1 -> MaterialTheme.typography.headlineLarge
            WihdTextStyle.H2 -> MaterialTheme.typography.headlineMedium
            WihdTextStyle.H3 -> MaterialTheme.typography.headlineSmall
            WihdTextStyle.T1 -> MaterialTheme.typography.titleLarge
            WihdTextStyle.T2 -> MaterialTheme.typography.titleMedium
            WihdTextStyle.T3 -> MaterialTheme.typography.titleSmall
            WihdTextStyle.B1 -> MaterialTheme.typography.bodyLarge
            WihdTextStyle.B2 -> MaterialTheme.typography.bodyMedium
            WihdTextStyle.B3 -> MaterialTheme.typography.bodySmall
            WihdTextStyle.L1 -> MaterialTheme.typography.labelLarge
            WihdTextStyle.L2 -> MaterialTheme.typography.labelMedium
            WihdTextStyle.L3 -> MaterialTheme.typography.labelSmall
        },
    )
}

enum class WihdTextStyle {
    Default,
    D1,
    D2,
    D3,
    H1,
    H2,
    H3,
    T1,
    T2,
    T3,
    L1,
    L2,
    L3,
    B1,
    B2,
    B3,
}

@Composable
fun LinkText(
    text: String,
    style: WihdTextStyle,
    url: String,
    modifier: Modifier = Modifier,
    fontStyle: FontStyle? = null,
    fontFamily: FontFamily? = null,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val uriHandler = LocalUriHandler.current
    WihdText(
        text = text,
        style = style,
        modifier = modifier.clickableWithoutRipple { uriHandler.openUri(url) },
        color = if (isSystemInDarkTheme()) dark_linkBlue else light_linkBlue,
        fontStyle = fontStyle,
        fontFamily = fontFamily,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
    )
}
