package co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.R

enum class MainDestination(
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    @StringRes val iconTextRes: Int,
    @StringRes val labelTextRes: Int,
) {
    NEWS(
        icon = Icons.Outlined.List,
        selectedIcon = Icons.Rounded.List,
        iconTextRes = R.string.main_news_description,
        labelTextRes = R.string.main_news_label,
    ),
    POST(
        icon = Icons.Outlined.AddCircle,
        selectedIcon = Icons.Rounded.AddCircle,
        iconTextRes = R.string.main_post_description,
        labelTextRes = R.string.main_post_label,
    ),
    PROFILE(
        icon = Icons.Outlined.Person,
        selectedIcon = Icons.Rounded.Person,
        iconTextRes = R.string.main_profile_description,
        labelTextRes = R.string.main_profile_label,
    ),
}