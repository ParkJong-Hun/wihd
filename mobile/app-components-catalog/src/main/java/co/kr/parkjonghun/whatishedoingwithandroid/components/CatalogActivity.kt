package co.kr.parkjonghun.whatishedoingwithandroid.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
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
                item {
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

@Composable
private fun CatalogRow(
    title: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    content: @Composable RowScope.() -> Unit,
) {
    val beautifulSize = remember { 16.dp }
    Column {
        Text(title)
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
