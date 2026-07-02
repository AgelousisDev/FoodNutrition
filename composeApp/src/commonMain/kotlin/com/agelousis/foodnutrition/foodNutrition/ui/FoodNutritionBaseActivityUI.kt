package com.agelousis.foodnutrition.foodNutrition.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.foodnutrition.compose.extensions.ImageLoaderConfiguration
import com.agelousis.foodnutrition.compose.views.ErrorMessage
import com.agelousis.foodnutrition.compose.views.Loader
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .alpha(
                                alpha =
                                    if (backStack.lastOrNull()?.appBarTitleInitialVisibility == true)
                                        1f
                                    else
                                        viewModel.appBarTitleAlpha
                            ),
                        text = viewModel.appBarTitle
                            ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    if (viewModel.navigationIcon != null)
                        FilledTonalIconButton(
                            onClick = {
                                if (backStack.size > 1)
                                    backStack.removeLastOrNull()
                                else
                                    onBackPress()
                            }
                        ) {
                            Icon(
                                imageVector = viewModel.navigationIcon
                                    ?: Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = viewModel.navigationIcon?.name,
                            )
                        }
                },
                actions = {
                    viewModel FoodNutritionBaseActivityNavigationBar backStack
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
        content = { contentPadding ->
            Navigation(
                contentPadding = contentPadding,
                viewModel = viewModel,
                backStack = backStack
            )
            viewModel.Loader()
        }
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Navigation(
    contentPadding: PaddingValues,
    viewModel: FoodNutritionBaseViewModel,
    backStack: SnapshotStateList<FoodNutritionNavigationScreen>
) {
    LaunchedEffect(
        key1 = backStack.size
    ) {
        backStack.lastOrNull()?.handleTopAppBar(
            viewModel = viewModel
        )
    }
    ExpressiveShapesBaseActivityNavigation(
        contentPadding = contentPadding,
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
