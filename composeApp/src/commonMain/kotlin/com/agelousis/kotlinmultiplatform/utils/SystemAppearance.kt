package com.agelousis.kotlinmultiplatform.utils

import androidx.compose.runtime.Composable

@Composable
expect fun SystemAppearance(
    isLight: Boolean
)