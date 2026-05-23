package com.agelousis.kotlinmultiplatform.foodNutrition.viewModel

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.agelousis.kotlinmultiplatform.foodNutrition.enumerations.FoodNutritionBaseActivityNavigationBarAction
import com.agelousis.kotlinmultiplatform.foodNutrition.navigation.FoodNutritionNavigationScreen
import com.agelousis.kotlinmultiplatform.utils.rememberShareManager
import kotlinx.coroutines.launch

@Composable
internal infix fun FoodNutritionBaseViewModel.FoodNutritionBaseActivityNavigationBar(
    backStack: SnapshotStateList<FoodNutritionNavigationScreen>
) {
    val shareManager = rememberShareManager()
    navigationBarActions.filterIsInstance<FoodNutritionBaseActivityNavigationBarAction>().forEach { navigationBarAction ->
        IconButton(
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
                    ?: return@IconButton,
                contentDescription = navigationBarAction.icon?.name
            )
        }
    }
}