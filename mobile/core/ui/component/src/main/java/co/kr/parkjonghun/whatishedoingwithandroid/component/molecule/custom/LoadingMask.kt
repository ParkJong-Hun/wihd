package co.kr.parkjonghun.whatishedoingwithandroid.component.molecule.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.IndicatorCircle

@Composable
fun LoadingMask(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    useShadow: Boolean = false,
) {
    Box(
        modifier = modifier
            .then(
                if (useShadow) {
                    Modifier.background(
                        color = MaterialTheme.colorScheme.scrim,
                    )
                } else {
                    Modifier
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        IndicatorCircle(
            isLoading = isLoading,
        )
    }
}
