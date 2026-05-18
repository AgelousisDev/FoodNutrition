package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.entryProvider
import com.agelousis.kotlinmultiplatform.compose.views.AppNavigation
import com.agelousis.kotlinmultiplatform.foodNutrition.navigation.FoodNutritionNavigationScreen
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel

@Composable
fun ExpressiveShapesBaseActivityNavigation(
    contentPadding: PaddingValues,
    viewModel: FoodNutritionBaseViewModel,
    backStack: SnapshotStateList<FoodNutritionNavigationScreen>
) {
    AppNavigation(
        contentPadding = contentPadding,
        backStack = backStack,
        entryProvider = entryProvider {
            entry<FoodNutritionNavigationScreen.FoodSearchScreen> {
                FoodSearchScreenView(
                    viewModel = viewModel,
                    foodDetailsRedirection = IngredientsDataResponseModel@ {
                        backStack.add(
                            element = FoodNutritionNavigationScreen.FoodDetailsScreen(
                                ingredientsDataResponseModel = this@IngredientsDataResponseModel
                            )
                        )
                    }
                )
            }
            entry<FoodNutritionNavigationScreen.FoodDetailsScreen> { (ingredientsDataResponseModel) ->
                FoodDetailsScreenView(
                    viewModel = viewModel,
                    ingredientsDataResponseModel = ingredientsDataResponseModel
                )
            }
            entry<FoodNutritionNavigationScreen.KetogenicSuperFoodsScreen> {
                KetogenicSuperFoodsScreenView(
                    viewModel = viewModel
                )
            }
        }
    )
}