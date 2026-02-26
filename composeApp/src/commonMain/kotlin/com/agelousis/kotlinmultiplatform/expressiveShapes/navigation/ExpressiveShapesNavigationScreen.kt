package com.agelousis.kotlinmultiplatform.expressiveShapes.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel.ExpressiveShapesBaseViewModel
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_expressive_shapes_label
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString

@Serializable
sealed class ExpressiveShapesNavigationScreen {

    @OptIn(ExperimentalResourceApi::class)
    suspend infix fun handleTopAppBar(
        viewModel: ExpressiveShapesBaseViewModel
    ) {
        when (this) {
            ExpressiveShapesScreen -> {
                viewModel.navigationIcon = Icons.AutoMirrored.Filled.ArrowBack
                viewModel.appBarTitle = getString(
                    resource = Res.string.key_expressive_shapes_label
                )
                viewModel.navigationBarActions.clear()
            }
        }
    }

    @Serializable
    data object ExpressiveShapesScreen: ExpressiveShapesNavigationScreen()

}