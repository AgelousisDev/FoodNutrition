package com.agelousis.kotlinmultiplatform.foodNutrition.enumerations

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.vector.ImageVector
import com.agelousis.kotlinmultiplatform.compose.models.NavigationBarAction
import com.agelousis.kotlinmultiplatform.theme.AvocadoIcon
import com.agelousis.kotlinmultiplatform.foodNutrition.navigation.FoodNutritionNavigationScreen

enum class FoodNutritionBaseActivityNavigationBarAction: NavigationBarAction {
    KETOGENIC_SUPER_FOODS;

    override val icon: ImageVector?
        get() = AvocadoIcon

    //backStack:

    infix fun action(
        backStack: SnapshotStateList<FoodNutritionNavigationScreen>
    ) {
        backStack.add(
            element = FoodNutritionNavigationScreen.KetogenicSuperFoodsScreen
        )
    }

}