package co.kr.parkjonghun.whatishedoingwithandroid.component.molecule.primitive

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SwitchableSuggestionChip(
    isClicked: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    enableColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
) {
    SuggestionChip(
        onClick = onClick,
        label = label,
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = if (isClicked) {
                enableColor
            } else {
                Color.Transparent
            },
        ),
    )
}
