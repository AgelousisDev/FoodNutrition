package com.agelousis.kotlinmultiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.ui.FoodNutritionBaseActivityView
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
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
        FoodNutritionBaseActivityView(
            viewModel = viewModel { FoodNutritionBaseViewModel() },
            onBackPress = ::exitApplication
        )
        //App()
    }
}
