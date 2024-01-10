package co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import co.kr.parkjonghun.whatishedoingwithandroid.system.theme.LocalTintTheme

@Composable
fun WihdImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val iconTint = LocalTintTheme.current.iconTint
    Image(
        painter = painter,
        contentScale = contentScale,
        contentDescription = contentDescription,
        modifier = modifier,
        colorFilter = if (iconTint != Color.Unspecified) ColorFilter.tint(iconTint) else null,
    )
}
