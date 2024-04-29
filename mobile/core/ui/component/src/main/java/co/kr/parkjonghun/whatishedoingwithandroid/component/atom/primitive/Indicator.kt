package co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.kr.parkjonghun.whatishedoingwithandroid.system.theme.SeedColors

private val defaultIndicatorSize = 64.dp

@Composable
fun ProgressCircle(
    progress: Int,
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        progress = { progress.toFloat() / 100 },
        modifier = modifier.size(defaultIndicatorSize),
        color = if (isSystemInDarkTheme()) SeedColors.Mascot.Dark else SeedColors.Mascot.Light,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun ProgressBar(
    progress: Int,
    modifier: Modifier = Modifier,
) {
    LinearProgressIndicator(
        progress = { progress.toFloat() / 100 },
        modifier = modifier.width(defaultIndicatorSize),
        color = if (isSystemInDarkTheme()) SeedColors.Mascot.Dark else SeedColors.Mascot.Light,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun IndicatorCircle(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    if (!isLoading) return
    CircularProgressIndicator(
        modifier = modifier.size(defaultIndicatorSize),
        color = if (isSystemInDarkTheme()) SeedColors.Mascot.Dark else SeedColors.Mascot.Light,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun IndicatorBar(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    if (!isLoading) return
    LinearProgressIndicator(
        modifier = modifier.width(defaultIndicatorSize),
        color = if (isSystemInDarkTheme()) SeedColors.Mascot.Dark else SeedColors.Mascot.Light,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}
