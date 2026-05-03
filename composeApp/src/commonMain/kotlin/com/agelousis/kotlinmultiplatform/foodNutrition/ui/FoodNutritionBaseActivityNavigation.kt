package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.agelousis.kotlinmultiplatform.foodNutrition.navigation.FoodNutritionNavigationScreen
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel

@Composable
fun ExpressiveShapesBaseActivityNavigation(
    contentPadding: PaddingValues,
    viewModel: FoodNutritionBaseViewModel,
    backStack: SnapshotStateList<FoodNutritionNavigationScreen>
) {
    NavDisplay(
        modifier = Modifier
            .padding(
                top = contentPadding.calculateTopPadding()
            ),
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
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