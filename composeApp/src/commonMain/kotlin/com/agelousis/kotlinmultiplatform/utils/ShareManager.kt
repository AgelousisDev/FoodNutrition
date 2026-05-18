package com.agelousis.kotlinmultiplatform.utils

import androidx.compose.runtime.Composable

@Composable
expect fun rememberShareManager(): ShareManager

interface  ShareManager {
    fun share(
        text: String,
        completion: () -> Unit = {}
    )
}