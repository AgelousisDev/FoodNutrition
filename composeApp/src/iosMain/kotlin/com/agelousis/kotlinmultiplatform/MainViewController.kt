package com.agelousis.kotlinmultiplatform

import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.ui.FoodNutritionBaseActivityView
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import platform.posix.exit

fun MainViewController() = ComposeUIViewController {
    FoodNutritionBaseActivityView(
        viewModel = viewModel { FoodNutritionBaseViewModel() },
        onBackPress = {
            exit(
                arg0 = 0
            )
        }
    )
}
