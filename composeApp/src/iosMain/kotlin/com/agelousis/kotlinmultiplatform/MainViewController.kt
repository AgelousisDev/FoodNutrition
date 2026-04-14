package com.agelousis.kotlinmultiplatform

import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.expressiveShapes.ui.ExpressiveShapesBaseActivityView
import com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel.ExpressiveShapesBaseViewModel
import platform.posix.exit

fun MainViewController() = ComposeUIViewController {
    ExpressiveShapesBaseActivityView(
        viewModel = viewModel { ExpressiveShapesBaseViewModel() },
        onBackPress = {
            exit(
                arg0 = 0
            )
        }
    )
}
