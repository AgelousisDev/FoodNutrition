package com.agelousis.foodnutrition.foodNutrition.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Close
import com.agelousis.foodnutrition.foodNutrition.enumerations.FoodNutritionBaseActivityNavigationBarAction
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
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
        viewModel.appBarTitle = when(this) {
            is FoodDetailsScreen ->
                viewModel.currentIngredientsDataResponseModelState?.modelFood?.label
                    ?: ""
            else ->
                title()
        }
        when(this) {
            is FoodSearchScreen -> {
                viewModel.navigationIcon = Icons.Outlined.Close
            }

            is FoodDetailsScreen -> {
                viewModel.navigationIcon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft
            }

            is KetogenicSuperFoodsScreen -> {
                viewModel.navigationIcon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft
            }
        }
        viewModel.navigationBarActions.clear()
        viewModel.navigationBarActions.addAll(
            elements = listOfNotNull(
                FoodNutritionBaseActivityNavigationBarAction.KETOGENIC_SUPER_FOODS,
                if (this is FoodDetailsScreen)
                    FoodNutritionBaseActivityNavigationBarAction.SHARE
                else
                    null
            )
        )
    }

    @Serializable
    data object FoodSearchScreen: FoodNutritionNavigationScreen() {
        override suspend fun title() = getStringArray(
            resource = Res.array.key_food_nutrition_screen_titles
        )[0]
    }
    @Serializable
    data class FoodDetailsScreen(
        val foodName: String
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