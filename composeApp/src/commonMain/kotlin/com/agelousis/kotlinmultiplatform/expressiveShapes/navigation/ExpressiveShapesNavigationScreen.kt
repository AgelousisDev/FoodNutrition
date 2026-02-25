package com.agelousis.kotlinmultiplatform.expressiveShapes.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel.ExpressiveShapesBaseViewModel
import kotlinx.serialization.Serializable

@Serializable
sealed class ExpressiveShapesNavigationScreen {

    infix fun handleTopAppBar(
        viewModel: ExpressiveShapesBaseViewModel
    ) {
        when (this) {
            ExpressiveShapesScreen -> {
                viewModel.navigationIcon = Icons.AutoMirrored.Filled.ArrowBack
                viewModel.appBarTitle = ""
                viewModel.navigationBarActions.clear()
            }
        }
    }

    @Serializable
    data object ExpressiveShapesScreen: ExpressiveShapesNavigationScreen()

}