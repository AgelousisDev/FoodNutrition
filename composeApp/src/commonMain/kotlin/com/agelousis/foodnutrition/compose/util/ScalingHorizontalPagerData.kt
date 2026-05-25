package com.agelousis.foodnutrition.compose.util

import androidx.compose.runtime.Composable

interface ScalingHorizontalPagerData {
    val icon: Any
    val label: String?
        @Composable get() = null
    val description: String?
        @Composable get() = null
}