package com.agelousis.foodnutrition.compose.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialTopBar(
    title: String? = null,
    appBarTitleAlpha: Float = 1f,
    navigationIcon: ImageVector? = null,
    navigationIconBlock: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    snackBarHost: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .alpha(
                                alpha = appBarTitleAlpha
                            ),
                        text = title
                            ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    if (navigationIcon != null)
                        FilledTonalIconButton(
                            onClick = navigationIconBlock
                        ) {
                            Icon(
                                imageVector = navigationIcon,
                                contentDescription = navigationIcon.name,
                            )
                        }
                },
                actions = actions
            )
        },
        contentWindowInsets = WindowInsets.statusBars,
        snackbarHost = snackBarHost,
        content = content
    )
}

