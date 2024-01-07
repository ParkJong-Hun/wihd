package co.kr.parkjonghun.whatishedoingwithandroid.components

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.LinkButton
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.PrimaryFilledButton
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.SecondaryFilledButton
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.TertiaryFilledButton
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.WihdText
import co.kr.parkjonghun.whatishedoingwithandroid.component.atom.primitive.WihdTextStyle
import co.kr.parkjonghun.whatishedoingwithandroid.system.extension.WihdPreview
import co.kr.parkjonghun.whatishedoingwithandroid.system.theme.MobileTheme

class CatalogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Catalog() }
    }
}

@Composable
private fun Catalog() {
    val beautifulSize = remember { 16.dp }
    val disabled = remember { false }
    MobileTheme {
        Surface {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = WindowInsets
                    .systemBars
                    .add(
                        WindowInsets(
                            left = beautifulSize,
                            top = beautifulSize,
                            right = beautifulSize,
                            bottom = beautifulSize,
                        ),
                    )
                    .asPaddingValues(),
                verticalArrangement = Arrangement.spacedBy(beautifulSize),
            ) {
                catalogHeadline()
                // TODO Search bar
                // TODO Filter chips
                catalogItem("Text") {
                    CatalogRow(title = "D1") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.D1)
                    }
                    CatalogRow(title = "D2") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.D2)
                    }
                    CatalogRow(title = "D3") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.D3)
                    }
                    CatalogRow(title = "H1") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.H1)
                    }
                    CatalogRow(title = "H2") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.H2)
                    }
                    CatalogRow(title = "H3") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.H3)
                    }
                    CatalogRow(title = "H1") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.T1)
                    }
                    CatalogRow(title = "H2") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.T2)
                    }
                    CatalogRow(title = "H3") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.T3)
                    }
                    CatalogRow(title = "H1") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.L1)
                    }
                    CatalogRow(title = "H2") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.L2)
                    }
                    CatalogRow(title = "H3") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.L3)
                    }
                    CatalogRow(title = "B1") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.B1)
                    }
                    CatalogRow(title = "B2") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.B2)
                    }
                    CatalogRow(title = "B3") {
                        WihdText(text = "Hello world !", style = WihdTextStyle.B3)
                    }
                }
                catalogItem(title = "Button") {
                    CatalogRow(title = "PrimaryFilledButton") {
                        PrimaryFilledButton(onClick = { /*TODO*/ }) {
                            Text("enabled")
                        }
                        PrimaryFilledButton(
                            onClick = { /*TODO*/ },
                            enabled = disabled,
                        ) {
                            Text("disabled")
                        }
                    }
                    CatalogRow(title = "SecondaryFilledButton") {
                        SecondaryFilledButton(onClick = { /*TODO*/ }) {
                            Text("enabled")
                        }
                        SecondaryFilledButton(
                            onClick = { /*TODO*/ },
                            enabled = disabled,
                        ) {
                            Text("disabled")
                        }
                    }
                    CatalogRow(title = "TertiaryFilledButton") {
                        TertiaryFilledButton(onClick = { /*TODO*/ }) {
                            Text("enabled")
                        }
                        TertiaryFilledButton(
                            onClick = { /*TODO*/ },
                            enabled = disabled,
                        ) {
                            Text("disabled")
                        }
                    }
                    CatalogRow(title = "LinkButton") {
                        LinkButton(onClick = { /*TODO*/ }) {
                            Text("enabled")
                        }
                        LinkButton(
                            onClick = { /*TODO*/ },
                            enabled = disabled,
                        ) {
                            Text("disabled")
                        }
                    }
                }
            }
        }
    }
}

private fun LazyListScope.catalogHeadline() {
    item {
        Column {
            WihdText(
                text = "WIHD Catalog",
                style = WihdTextStyle.D3,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(thickness = 3.dp)
        }
    }
}

private fun LazyListScope.catalogItem(
    title: String,
    content: @Composable LazyItemScope.() -> Unit,
) {
    item {
        Column {
            WihdText(
                text = title,
                style = WihdTextStyle.H2,
                color = MaterialTheme.colorScheme.secondary,
            )
            content()
            Divider()
        }
    }
}

@Composable
private fun CatalogRow(
    title: String,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier.fillMaxWidth(),
    content: @Composable RowScope.() -> Unit,
) {
    val beautifulSize = remember { 16.dp }
    Column {
        WihdText(
            text = title,
            style = WihdTextStyle.L2,
            color = MaterialTheme.colorScheme.tertiary,
        )
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(beautifulSize),
        ) {
            content()
        }
    }
}

@WihdPreview
@Composable
private fun CatalogPreview() {
    MobileTheme {
        Catalog()
    }
}
