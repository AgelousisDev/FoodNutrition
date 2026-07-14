package com.agelousis.foodnutrition.compose.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector

data class AppBarUiState(
    val title: String,
    val navigationIcon: ImageVector = Icons.AutoMirrored.Outlined.ArrowBack,
    val actions: List<NavigationBarAction> = emptyList()
)