package com.agelousis.kotlinmultiplatform.utils

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

class AndroidShareManager(
    private val context: Context
) : ShareManager {
    override fun share(
        text: String,
        completion: () -> Unit
    ) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        context.startActivity(intent)
    }
}

@Composable
actual fun rememberShareManager(): ShareManager {
    val context = LocalContext.current
    return remember(
        key1 = context
    ) {
        AndroidShareManager(
            context = context
        )
    }
}