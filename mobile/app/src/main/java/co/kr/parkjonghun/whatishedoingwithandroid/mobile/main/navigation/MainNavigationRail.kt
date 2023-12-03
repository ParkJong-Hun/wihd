package co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.kr.parkjonghun.whatishedoingwithandroid.ui.extension.WihdPreview
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MainNavigationRail(
    selectedRailItem: MainDestination,
    mainRailItems: PersistentList<MainDestination>,
    onRailItemSelected: (MainDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(
        modifier = modifier,
    ) {
        mainRailItems.forEach { item ->
            val isCurrentRailItem = item == selectedRailItem
            val contentDescription = stringResource(id = item.iconTextRes)
            NavigationRailItem(
                selected = item == selectedRailItem,
                onClick = { onRailItemSelected(item) },
                icon = {
                    if (isCurrentRailItem) {
                        Icon(
                            imageVector = item.selectedIcon,
                            contentDescription = contentDescription,
                        )
                    } else {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = contentDescription,
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(id = item.labelTextRes),
                    )
                },
            )
        }
    }
}

@WihdPreview
@Composable
private fun MainNavigationRailPreview() {
    MainNavigationRail(
        selectedRailItem = MainDestination.NEWS,
        mainRailItems = MainDestination.entries.toPersistentList(),
        onRailItemSelected = {},
        modifier = Modifier,
    )
}
