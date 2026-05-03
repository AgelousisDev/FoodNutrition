package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.agelousis.kotlinmultiplatform.foodNutrition.enumerations.FoodNutritionBaseActivityNavigationBarAction
import com.agelousis.kotlinmultiplatform.foodNutrition.navigation.FoodNutritionNavigationScreen
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel

@Composable
internal fun FoodNutritionBaseActivityNavigationBar(
    viewModel: FoodNutritionBaseViewModel,
    backStack: SnapshotStateList<FoodNutritionNavigationScreen>
) {
    viewModel.navigationBarActions.filterIsInstance<FoodNutritionBaseActivityNavigationBarAction>().forEach { navigationBarAction ->
        IconButton(
            onClick = {
                navigationBarAction action backStack
            }
        ) {
            Icon(
                imageVector = navigationBarAction.icon
                    ?: return@IconButton,
                contentDescription = navigationBarAction.icon?.name
            )
        }
    }
}