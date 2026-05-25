package com.agelousis.foodnutrition

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.foodnutrition.foodNutrition.ui.FoodNutritionBaseActivityView
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.foodnutrition.utils.DataStoreProvider
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
        val dataStore = remember {
            DataStoreProvider().createDataStore()
        }
        FoodNutritionBaseActivityView(
            viewModel = viewModel {
                FoodNutritionBaseViewModel(
                    dataStore = dataStore
                )
            },
            onBackPress = ::exitApplication
        )
        //App()
    }
}
