package com.agelousis.kotlinmultiplatform.expressiveShapes.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import com.agelousis.kotlinmultiplatform.R
import com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel.ExpressiveShapesBaseViewModel
import kotlinx.serialization.Serializable

@Serializable
sealed class ExpressiveShapesNavigationScreen {

    fun handleTopAppBar(
        context: Context,
        viewModel: ExpressiveShapesBaseViewModel
    ) {
        when (this) {
            ExpressiveShapesScreen -> {
                viewModel.navigationIcon = Icons.AutoMirrored.Filled.ArrowBack
                viewModel.appBarTitle = context.resources.getString(R.string.app_name)
                viewModel.navigationBarActions.clear()
            }
        }
    }

    @Serializable
    data object ExpressiveShapesScreen: ExpressiveShapesNavigationScreen()

}