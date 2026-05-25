package com.agelousis.foodnutrition

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.foodnutrition.foodNutrition.ui.FoodNutritionBaseActivityView
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.foodnutrition.utils.DataStoreProvider
import platform.posix.exit

fun MainViewController() = ComposeUIViewController {
    val dataStore = remember {
        DataStoreProvider().createDataStore()
    }
    FoodNutritionBaseActivityView(
        viewModel = viewModel {
            FoodNutritionBaseViewModel(
                dataStore = dataStore
            )
        },
        onBackPress = {
            exit(
                arg0 = 0
            )
        }
    )
}
