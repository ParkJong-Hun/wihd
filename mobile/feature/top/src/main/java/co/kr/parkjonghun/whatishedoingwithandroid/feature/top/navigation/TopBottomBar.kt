package co.kr.parkjonghun.whatishedoingwithandroid.feature.top.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.kr.parkjonghun.whatishedoingwithandroid.system.extension.WihdPreview
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun TopBottomBar(
    selectedRailItem: TopDestination,
    mainTabs: PersistentList<TopDestination>,
    onRailItemSelected: (TopDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        mainTabs.forEach { item ->
            val isCurrentRailItem = item == selectedRailItem
            val contentDescription = stringResource(id = item.iconTextRes)
            NavigationBarItem(
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
private fun TopBottomBarPreview() {
    TopBottomBar(
        selectedRailItem = TopDestination.NEWS,
        mainTabs = TopDestination.entries.toPersistentList(),
        onRailItemSelected = {},
        modifier = Modifier,
    )
}
