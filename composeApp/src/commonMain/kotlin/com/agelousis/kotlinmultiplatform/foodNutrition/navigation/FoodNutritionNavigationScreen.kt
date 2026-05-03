package com.agelousis.kotlinmultiplatform.foodNutrition.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_food_nutrition_screen_titles
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getStringArray

@Serializable
sealed class FoodNutritionNavigationScreen {

    abstract suspend fun title(): String

    @OptIn(ExperimentalResourceApi::class)
    suspend infix fun handleTopAppBar(
        viewModel: FoodNutritionBaseViewModel
    ) {
        viewModel.appBarTitle = title()
        viewModel.navigationIcon = Icons.AutoMirrored.Filled.ArrowBack
        viewModel.navigationBarActions.clear()
    }

    @Serializable
    data object FoodSearchScreen: FoodNutritionNavigationScreen() {
        override suspend fun title() = getStringArray(
            resource = Res.array.key_food_nutrition_screen_titles
        )[0]
    }
    @Serializable
    data class FoodDetailsScreen(
        val ingredientsDataResponseModel: IngredientsDataResponseModel
    ): FoodNutritionNavigationScreen() {
        override suspend fun title() = getStringArray(
            resource = Res.array.key_food_nutrition_screen_titles
        )[1]
    }
    @Serializable
    data object KetogenicSuperFoodsScreen: FoodNutritionNavigationScreen() {
        override suspend fun title() = getStringArray(
            resource = Res.array.key_food_nutrition_screen_titles
        )[2]
    }

}