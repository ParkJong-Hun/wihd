package co.kr.parkjonghun.whatishedoingwithandroid.component.molecule.custom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.IndicatorCircle
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.WihdImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter =
        painterResource(co.kr.parkjonghun.whatishedoingwithandroid.ui.system.R.drawable.ic_placeholder_default),
) {
    val isLocalInspection = LocalInspectionMode.current
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = imageUrl,
        onState = { state ->
            isLoading = state is AsyncImagePainter.State.Loading
            isError = state is AsyncImagePainter.State.Error
        },
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        IndicatorCircle(
            isLoading = isLoading && !isLocalInspection,
            modifier = Modifier
                .align(Alignment.Center)
                .size(80.dp),
        )
        WihdImage(
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && !isLocalInspection) imageLoader else placeholder,
            contentDescription = contentDescription,
        )
    }
}
