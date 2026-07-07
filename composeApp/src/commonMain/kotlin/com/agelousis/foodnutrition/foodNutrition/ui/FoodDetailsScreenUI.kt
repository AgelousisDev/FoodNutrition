package com.agelousis.foodnutrition.foodNutrition.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.foodnutrition.compose.extensions.shimmerEffect
import com.agelousis.foodnutrition.foodNutrition.ui.views.FoodInfoView
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.foodnutrition.network.response.FoodModel
import com.agelousis.foodnutrition.network.response.INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
import com.agelousis.foodnutrition.network.response.MeasureModel
import com.agelousis.foodnutrition.theme.AppTheme
import com.agelousis.foodnutrition.theme.Steel

@Composable
fun FoodDetailsScreenView(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel,
    foodName: String
) {
    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
    val (foodColor, setFoodColor) = remember {
        mutableStateOf(
            value = Steel
        )
    }
    val lazyListState = rememberLazyListState()
    val headerAlpha = headerConfiguration(
        lazyListState = lazyListState,
        viewModel = viewModel
    )
    /*val headerImageIsNotVisible by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }
    SystemAppearance(
        isLight =
            if (headerImageIsNotVisible)
                !isSystemInDarkTheme()
            else
                foodColor.luminance() > .5f
    )*/
    //region Request food nutrition
    RequestFoodNutrition(
        viewModel = viewModel,
        foodName = foodName
    )
    //endregion
    Surface(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyListState,
            contentPadding = PaddingValues(
                bottom = navigationBarsPadding.calculateBottomPadding()
            )
        ) {
            //region Image
            item {
                Box(
                    modifier = Modifier
                        .animateItem()
                ) {
                    val modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            height = 300.dp
                        )
                        .animateItem()
                    viewModel.currentIngredientsDataResponseModelState?.FoodImage(
                        modifier = modifier,
                        color = setFoodColor
                    ) ?: Box(
                        modifier = modifier
                            .shimmerEffect()
                    )
                }
            }
            //endregion
            //region Food Info Card
            item {
                FoodInfoView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(
                            y = (-24).dp
                        )
                        .animateItem(),
                    viewModel = viewModel,
                    headerAlpha = headerAlpha,
                    foodColor = foodColor
                )
            }
            //endregion
        }
    }
}

@Composable
private fun headerConfiguration(
    lazyListState: LazyListState,
    viewModel: FoodNutritionBaseViewModel
): Float {
    //region Header Configuration
    val headerAlpha by remember {
        derivedStateOf {
            if (lazyListState.firstVisibleItemIndex == 1)
                1f
            else
                (lazyListState.firstVisibleItemScrollOffset / 200f).coerceIn(
                    minimumValue = 0f,
                    maximumValue = 1f
                )
        }
    }
    LaunchedEffect(
        key1 = headerAlpha
    ) {
        viewModel.appBarTitleAlpha = headerAlpha
    }
    //endregion
    return headerAlpha
}

@Composable
private fun RequestFoodNutrition(
    viewModel: FoodNutritionBaseViewModel,
    foodName: String
) {
    LaunchedEffect(
        key1 = Unit
    ) {
        viewModel.requestFoodNutrition(
            foodName = foodName
        )
    }
}

@Preview(heightDp = 2000)
@Composable
fun FoodDetailsScreenViewPreview() {
    AppTheme {
        FoodDetailsScreenView(
            viewModel = viewModel {
                FoodNutritionBaseViewModel(
                    dataStore = null
                ).also { viewModel ->
                    viewModel.currentIngredientsDataResponseModelState = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL?.copy(
                        modelFood = FoodModel(
                            category = "Generic Foods",
                            label = "Avocado"
                        ),
                        measures = listOf(
                            MeasureModel(
                                uri = null,
                                label = "Serving",
                                weight = 100.0
                            ),
                            MeasureModel(
                                uri = null,
                                label = "Whole",
                                weight = 10.0
                            ),
                            MeasureModel(
                                uri = null,
                                label = "Strip",
                                weight = 10.0
                            )
                        )
                    ) ?: return@also
                }
            },
            foodName = "Avocado"
        )
    }
}

@Preview(widthDp = 1200, heightDp = 1800)
@Composable
fun FoodDetailsScreenViewInLandscapePreview() {
    AppTheme {
        FoodDetailsScreenView(
            viewModel = viewModel {
                FoodNutritionBaseViewModel(
                    dataStore = null
                ).also { viewModel ->
                    viewModel.currentIngredientsDataResponseModelState = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL?.copy(
                        modelFood = FoodModel(
                            category = "Generic Foods",
                            label = "Avocado"
                        ),
                        measures = listOf(
                            MeasureModel(
                                uri = null,
                                label = "Serving",
                                weight = 100.0
                            ),
                            MeasureModel(
                                uri = null,
                                label = "Whole",
                                weight = 10.0
                            ),
                            MeasureModel(
                                uri = null,
                                label = "Strip",
                                weight = 10.0
                            )
                        )
                    ) ?: return@also
                }
            },
            foodName = "Avocado"
        )
    }
}

@Preview(showBackground = true, heightDp = 2000)
@Composable
fun FoodDetailsScreenViewPreviewDarkMode() {
    AppTheme(
        darkTheme = true
    ) {
        FoodDetailsScreenView(
            viewModel = viewModel {
                FoodNutritionBaseViewModel(
                    dataStore = null
                ).also { viewModel ->
                    viewModel.currentIngredientsDataResponseModelState = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL?.copy(
                        modelFood = FoodModel(
                            category = "Generic Foods",
                            label = "Avocado"
                        ),
                        measures = listOf(
                            MeasureModel(
                                uri = null,
                                label = "Serving",
                                weight = 100.0
                            ),
                            MeasureModel(
                                uri = null,
                                label = "Whole",
                                weight = 10.0
                            ),
                            MeasureModel(
                                uri = null,
                                label = "Strip",
                                weight = 10.0
                            )
                        )
                    ) ?: return@also
                }
            },
            foodName = "Avocado"
        )
    }
}
