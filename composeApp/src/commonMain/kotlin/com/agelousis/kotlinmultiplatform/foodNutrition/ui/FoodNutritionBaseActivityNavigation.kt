package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
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
        backStack = backStack,
        entryProvider = entryProvider {
            entry<FoodNutritionNavigationScreen.FoodSearchScreen> {
                FoodSearchScreenView(
                    modifier = Modifier
                        .padding(
                            top = contentPadding.calculateTopPadding()
                        ),
                    viewModel = viewModel,
                    foodDetailsRedirection = IngredientsDataResponseModel@ {
                        viewModel.currentIngredientsDataResponseModelState = this@IngredientsDataResponseModel
                        backStack.add(
                            element = FoodNutritionNavigationScreen.FoodDetailsScreen
                        )
                    }
                )
            }
            entry<FoodNutritionNavigationScreen.FoodDetailsScreen> {
                FoodDetailsScreenView(
                    viewModel = viewModel
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