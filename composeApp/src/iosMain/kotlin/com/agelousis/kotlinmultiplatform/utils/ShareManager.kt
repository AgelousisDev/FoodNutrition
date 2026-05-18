package com.agelousis.kotlinmultiplatform.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

class IosShareManager : ShareManager {
    override fun share(
        text: String,
        completion: () -> Unit
    ) {
        val window = UIApplication.sharedApplication.keyWindow
        val activityController = UIActivityViewController(
            activityItems = listOf(text),
            applicationActivities = null
        )
        window?.rootViewController?.presentViewController(
            viewControllerToPresent = activityController,
            animated = true,
            completion = null
        )
    }
}

@Composable
actual fun rememberShareManager(): ShareManager = remember {
    IosShareManager()
}