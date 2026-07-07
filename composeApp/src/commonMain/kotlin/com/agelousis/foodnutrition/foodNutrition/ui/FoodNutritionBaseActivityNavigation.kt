package com.agelousis.foodnutrition.foodNutrition.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import com.agelousis.foodnutrition.compose.views.AppNavigation
import com.agelousis.foodnutrition.foodNutrition.navigation.FoodNutritionNavigationScreen
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.foodnutrition.foodNutrition.viewModel.parseFoodErrorState

@Composable
fun FoodNutritionBaseActivityNavigation(
    contentPadding: PaddingValues,
    viewModel: FoodNutritionBaseViewModel,
    backStack: SnapshotStateList<FoodNutritionNavigationScreen>
) {
    with(
        receiver = viewModel
    ) {
        HandleFoodParsingError(
            backStack = backStack
        )
    }
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
                    foodDetailsRedirection = FoodName@ {
                        //viewModel.currentIngredientsDataResponseModelState = this@IngredientsDataResponseModel
                        backStack.add(
                            element = FoodNutritionNavigationScreen.FoodDetailsScreen(
                                foodName = this@FoodName
                            )
                        )
                    }
                )
            }
            entry<FoodNutritionNavigationScreen.FoodDetailsScreen> { (foodName) ->
                FoodDetailsScreenView(
                    modifier = Modifier
                        .padding(
                            top = contentPadding.calculateTopPadding()
                        ),
                    viewModel = viewModel,
                    foodName = foodName
                )
            }
            entry<FoodNutritionNavigationScreen.KetogenicSuperFoodsScreen> {
                KetogenicSuperFoodsScreenView(
                    modifier = Modifier
                        .padding(
                            top = contentPadding.calculateTopPadding()
                        ),
                    viewModel = viewModel
                )
            }
        }
    )
}

context(viewModel: FoodNutritionBaseViewModel)
@Composable
private fun HandleFoodParsingError(
    backStack: SnapshotStateList<FoodNutritionNavigationScreen>
) {
    LaunchedEffect(
        key1 = viewModel.parseFoodErrorState
    ) {
        if (viewModel.parseFoodErrorState) {
            backStack.removeLastOrNull()
            viewModel.parseFoodErrorState = false
        }
    }
}