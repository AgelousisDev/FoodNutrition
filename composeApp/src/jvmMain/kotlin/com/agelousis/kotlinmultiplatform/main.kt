package com.agelousis.kotlinmultiplatform

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.expressiveShapes.extensions.ExpressiveShapesBaseActivityNavigation
import com.agelousis.kotlinmultiplatform.expressiveShapes.navigation.ExpressiveShapesNavigationScreen
import com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel.ExpressiveShapesBaseViewModel

fun main() = application {
    val backStack = remember {
        mutableStateListOf<ExpressiveShapesNavigationScreen>(
            ExpressiveShapesNavigationScreen.ExpressiveShapesScreen
        )
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinMultiplatform",
    ) {
        ExpressiveShapesBaseActivityNavigation(
            contentPadding = PaddingValues(
                all = 0.dp
            ),
            viewModel = viewModel { ExpressiveShapesBaseViewModel() },
            backStack = backStack
        )
        //App()
    }
}
