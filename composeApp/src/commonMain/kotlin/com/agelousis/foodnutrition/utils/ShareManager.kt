package com.agelousis.foodnutrition.utils

import androidx.compose.runtime.Composable

@Composable
expect fun rememberShareManager(): ShareManager

interface  ShareManager {
    fun share(
        text: String,
        completion: () -> Unit = {}
    )
}