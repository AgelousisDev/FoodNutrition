package com.agelousis.kotlinmultiplatform.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarStyleDarkContent
import platform.UIKit.UIStatusBarStyleLightContent
import platform.UIKit.setStatusBarStyle

@Composable
actual fun SystemAppearance(
    isLight: Boolean
) {
    SideEffect {
        UIApplication.sharedApplication.setStatusBarStyle(
            statusBarStyle = if (isLight) UIStatusBarStyleDarkContent else UIStatusBarStyleLightContent
        )
    }
}