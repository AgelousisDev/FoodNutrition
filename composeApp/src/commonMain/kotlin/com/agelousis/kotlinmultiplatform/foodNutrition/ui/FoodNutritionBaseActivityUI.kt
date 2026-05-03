package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.compose.views.ErrorMessage
import com.agelousis.kotlinmultiplatform.foodNutrition.navigation.FoodNutritionNavigationScreen
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import com.agelousis.kotlinmultiplatform.utils.SuccessUnitBlock
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodNutritionBaseActivityView(
    viewModel: FoodNutritionBaseViewModel,
    onBackPress: SuccessUnitBlock
) {
    val backStack = remember {
        mutableStateListOf<FoodNutritionNavigationScreen>(
            FoodNutritionNavigationScreen.FoodSearchScreen
        )
    }
    viewModel.ErrorMessage()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = viewModel.appBarTitle
                            ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
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
                    FoodNutritionBaseActivityNavigationBar(
                        viewModel = viewModel,
                        backStack = backStack
                    )
                }
            )
        },
        /*floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    backStack.add(
                        element = FoodNutritionNavigationScreen.KetogenicSuperFoodsScreen
                    )
                }
            ) {
                Text(
                    text = stringResource(
                        resource = Res.string.key_ketogenic_super_foods_label
                    ),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,*/
        content = { contentPadding ->
            Navigation(
                contentPadding = contentPadding,
                viewModel = viewModel,
                backStack = backStack
            )
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
