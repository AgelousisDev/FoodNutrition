package com.agelousis.foodnutrition.foodNutrition.viewModel

import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.agelousis.foodnutrition.foodNutrition.enumerations.FoodNutritionBaseActivityNavigationBarAction
import com.agelousis.foodnutrition.foodNutrition.navigation.FoodNutritionNavigationScreen
import com.agelousis.foodnutrition.utils.rememberShareManager
import kotlinx.coroutines.launch

@Composable
internal infix fun FoodNutritionBaseViewModel.FoodNutritionBaseActivityNavigationBar(
    backStack: SnapshotStateList<FoodNutritionNavigationScreen>
) {
    val shareManager = rememberShareManager()
    navigationBarActions.filterIsInstance<FoodNutritionBaseActivityNavigationBarAction>().forEach { navigationBarAction ->
        FilledTonalIconButton(
            onClick = {
                viewModelScope.launch {
                    navigationBarAction.action(
                        viewModel = this@FoodNutritionBaseActivityNavigationBar,
                        backStack = backStack,
                        shareManager = shareManager,
                        data = when(navigationBarAction) {
                            FoodNutritionBaseActivityNavigationBarAction.SHARE ->
                                currentIngredientsDataResponseModelState?.shareableDetails()
                            else ->
                                null
                        }
                    )
                }
            }
        ) {
            Icon(
                imageVector = navigationBarAction.icon
                    ?: return@FilledTonalIconButton,
                contentDescription = navigationBarAction.icon?.name
            )
        }
    }
}