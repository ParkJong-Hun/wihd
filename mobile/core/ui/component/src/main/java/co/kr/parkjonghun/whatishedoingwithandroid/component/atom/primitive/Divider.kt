package co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThickDivider(
    modifier: Modifier = Modifier,
) {
    Divider(
        modifier = modifier,
        thickness = 3.dp,
    )
}
