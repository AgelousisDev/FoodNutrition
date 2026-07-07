package com.agelousis.foodnutrition.foodNutrition.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.foodnutrition.compose.extensions.ImageLoaderConfiguration
import com.agelousis.foodnutrition.compose.views.ErrorMessage
import com.agelousis.foodnutrition.compose.views.Loader
import com.agelousis.foodnutrition.compose.views.MaterialTopBar
import com.agelousis.foodnutrition.compose.views.SnackBarMessage
import com.agelousis.foodnutrition.foodNutrition.navigation.FoodNutritionNavigationScreen
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseActivityNavigationBar
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.foodnutrition.theme.AppTheme
import com.agelousis.foodnutrition.utils.SuccessUnitBlock
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodNutritionBaseActivityView(
    viewModel: FoodNutritionBaseViewModel,
    onBackPress: SuccessUnitBlock
) {
    ImageLoaderConfiguration()
    val backStack = remember {
        mutableStateListOf<FoodNutritionNavigationScreen>(
            FoodNutritionNavigationScreen.FoodSearchScreen
        )
    }
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    viewModel.ErrorMessage()
    viewModel SnackBarMessage snackBarHostState
    MaterialTopBar(
        title = viewModel.appBarTitle,
        appBarTitleAlpha = viewModel.appBarTitleAlpha,
        navigationIcon = viewModel.navigationIcon,
        navigationIconBlock = {
            if (backStack.size > 1)
                backStack.removeLastOrNull()
            else
                onBackPress()
        },
        actions = {
            viewModel FoodNutritionBaseActivityNavigationBar backStack
        },
        snackBarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
        content = {
            Navigation(
                viewModel = viewModel,
                backStack = backStack
            )
            viewModel.Loader()
        }
    )

}

context(paddingValues: PaddingValues)
@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Navigation(
    viewModel: FoodNutritionBaseViewModel,
    backStack: SnapshotStateList<FoodNutritionNavigationScreen>
) {
    LaunchedEffect(
        key1 = backStack.size,
        key2 = viewModel.currentIngredientsDataResponseModelState
    ) {
        backStack.lastOrNull()?.handleTopAppBar(
            viewModel = viewModel
        )
    }
    FoodNutritionBaseActivityNavigation(
        contentPadding = paddingValues,
        viewModel = viewModel,
        backStack = backStack
    )
}

@Preview
@Composable
fun FoodNutritionBaseActivityViewPreview() {
    AppTheme {
        FoodNutritionBaseActivityView(
            viewModel = viewModel(),
            onBackPress = {}
        )
    }
}
