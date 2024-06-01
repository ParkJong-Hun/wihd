package co.kr.parkjonghun.whatishedoingwithandroid.component.molecule.primitive

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import co.kr.parkjonghun.whatishedoingwithandroid.system.theme.SeedColors

@Composable
fun SwitchableSuggestionChip(
    isClicked: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    enableColor: Color = if (isSystemInDarkTheme()) SeedColors.Black.GrayDark else SeedColors.Black.GrayLight,
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
