package com.agelousis.kotlinmultiplatform.foodNutrition.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_ketogenic_super_foods_label
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString

@Serializable
sealed class FoodNutritionNavigationScreen {

    @OptIn(ExperimentalResourceApi::class)
    suspend infix fun handleTopAppBar(
        viewModel: FoodNutritionBaseViewModel
    ) {
        when (this) {
            KetogenicSuperFoodsScreen -> {
                viewModel.navigationIcon = Icons.AutoMirrored.Filled.ArrowBack
                viewModel.appBarTitle = getString(
                    resource = Res.string.key_ketogenic_super_foods_label
                )
                viewModel.navigationBarActions.clear()
            }
        }
    }

    @Serializable
    data object KetogenicSuperFoodsScreen: FoodNutritionNavigationScreen()

}