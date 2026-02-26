package com.agelousis.kotlinmultiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.expressiveShapes.ui.ExpressiveShapesBaseActivityView
import com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel.ExpressiveShapesBaseViewModel
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_app_name_label
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(
            resource = Res.string.key_app_name_label
        )
    ) {
        ExpressiveShapesBaseActivityView(
            viewModel = viewModel { ExpressiveShapesBaseViewModel() },
            onBackPress = ::exitApplication
        )
        //App()
    }
}
