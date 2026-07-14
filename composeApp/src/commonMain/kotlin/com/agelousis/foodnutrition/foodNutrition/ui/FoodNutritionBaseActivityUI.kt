package com.agelousis.foodnutrition.foodNutrition.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.foodnutrition.compose.extensions.ImageLoaderConfiguration
import com.agelousis.foodnutrition.compose.models.AppBarUiState
import com.agelousis.foodnutrition.compose.views.ErrorMessage
import com.agelousis.foodnutrition.compose.views.Loader
import com.agelousis.foodnutrition.compose.views.MaterialTopBar
import com.agelousis.foodnutrition.compose.views.SnackBarMessage
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.foodnutrition.theme.AppTheme
import com.agelousis.foodnutrition.utils.SuccessUnitBlock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodNutritionBaseActivityView(
    viewModel: FoodNutritionBaseViewModel,
    onBackPress: SuccessUnitBlock
) {
    ImageLoaderConfiguration()
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    viewModel.ErrorMessage()
    viewModel SnackBarMessage snackBarHostState
    val currentScreen = viewModel.navigationScreens.lastOrNull()
    val appBarUiState by produceState(
        initialValue = AppBarUiState(
            title = "",
            navigationIcon = currentScreen?.navigationIcon
                ?: Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
            actions = currentScreen?.navigationBarActions
                ?: emptyList()
        ),
        key1 = currentScreen,
        key2 = viewModel.currentIngredientsDataResponseModelState
    ) {
        value = value.copy(
            title = currentScreen?.title(
                viewModel = viewModel
            ) ?: "",
            navigationIcon = currentScreen?.navigationIcon
                ?: Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
            actions = currentScreen?.navigationBarActions
                ?: emptyList()
        )
    }
    MaterialTopBar(
        title = appBarUiState.title,
        appBarTitleAlpha = viewModel.appBarTitleAlpha,
        navigationIcon = appBarUiState.navigationIcon,
        navigationIconBlock = {
            if (viewModel.navigationScreens.size > 1)
                viewModel.navigationScreens.removeLastOrNull()
            else
                onBackPress()
        },
        actions = {
            viewModel.navigationScreens.lastOrNull()?.FoodNutritionBaseActivityNavigationBar(
                viewModel = viewModel
            )
        },
        snackBarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
        content = PaddingValues@ {
            FoodNutritionBaseActivityNavigation(
                contentPadding = this@PaddingValues,
                viewModel = viewModel
            )
            viewModel.Loader()
        }
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
