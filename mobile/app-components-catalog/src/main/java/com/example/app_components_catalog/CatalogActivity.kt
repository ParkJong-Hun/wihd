package com.example.app_components_catalog

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
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.kr.parkjonghun.whatishedoingwithandroid.ui.theme.MobileTheme

class CatalogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Catalog() }
    }
}

@Composable
fun Catalog() {
    val beautifulSize = remember { 16.dp }
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
                    CatalogRow(title = "Sample Button") {
                        Button(onClick = {}) {
                            Text("Button")
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
    content: @Composable RowScope.() -> Unit,
) {
    Column {
        Text(title)
        Row {
            content()
        }
    }
}
