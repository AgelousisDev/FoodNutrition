package com.agelousis.kotlinmultiplatform.compose.models

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle

interface NavigationBarAction {
    val icon: ImageVector?
        get() = null
    val drawableResourceId: Int?
        get() = null
    val label: Int?
        get() = null
    val labelStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.labelMedium

}