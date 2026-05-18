package com.agelousis.kotlinmultiplatform.theme

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
actual fun AppTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme =
        if (darkTheme)
            dynamicDarkColorScheme(
                context = context
            )
        else
            dynamicLightColorScheme(
                context = context
            )

    MaterialExpressiveTheme(
        colorScheme = colorScheme,
        content = content
    )
}