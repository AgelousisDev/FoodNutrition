package com.agelousis.foodnutrition.foodNutrition.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.agelousis.foodnutrition.foodNutrition.enumerations.FoodNutritionBaseActivityNavigationBarAction
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.foodnutrition.utils.rememberShareManager
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_food_nutrition_screen_titles
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.getStringArray

@Serializable
sealed class FoodNutritionNavigationScreen {

    abstract suspend infix fun title(
        viewModel: FoodNutritionBaseViewModel
    ): String

    val navigationIcon
        get() = when (this) {
        is FoodSearchScreen ->
            Icons.Outlined.Close

        is FoodDetailsScreen ->
            Icons.AutoMirrored.Outlined.KeyboardArrowLeft

        is KetogenicSuperFoodsScreen ->
            Icons.AutoMirrored.Outlined.KeyboardArrowLeft
    }

    val navigationBarActions
        get() = listOfNotNull(
            FoodNutritionBaseActivityNavigationBarAction.KETOGENIC_SUPER_FOODS,
            if (this is FoodDetailsScreen)
                FoodNutritionBaseActivityNavigationBarAction.SHARE
            else
                null
        )

    @Composable
    infix fun FoodNutritionBaseActivityNavigationBar(
        viewModel: FoodNutritionBaseViewModel
    ) {
        val scope = rememberCoroutineScope()
        val shareManager = rememberShareManager()
        navigationBarActions.forEach { navigationBarAction ->
            FilledTonalIconButton(
                onClick = {
                    scope.launch {
                        navigationBarAction.action(
                            viewModel = viewModel,
                            shareManager = shareManager,
                            data = when(navigationBarAction) {
                                FoodNutritionBaseActivityNavigationBarAction.SHARE ->
                                    viewModel.currentIngredientsDataResponseModelState?.shareableDetails()
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

    @Serializable
    data object FoodSearchScreen: FoodNutritionNavigationScreen() {
        override suspend fun title(
            viewModel: FoodNutritionBaseViewModel
        ) = getStringArray(
            resource = Res.array.key_food_nutrition_screen_titles
        )[0]
    }
    @Serializable
    data class FoodDetailsScreen(
        val foodName: String
    ): FoodNutritionNavigationScreen() {
        override suspend fun title(
            viewModel: FoodNutritionBaseViewModel
        ) = viewModel.currentIngredientsDataResponseModelState?.modelFood?.label ?: ""
    }
    @Serializable
    data object KetogenicSuperFoodsScreen: FoodNutritionNavigationScreen() {
        override suspend fun title(
            viewModel: FoodNutritionBaseViewModel
        ) = getStringArray(
            resource = Res.array.key_food_nutrition_screen_titles
        )[2]
    }

}