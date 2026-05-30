package com.agelousis.foodnutrition.foodNutrition.ui

import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.foodnutrition.foodNutrition.ui.views.FoodInfoView
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.foodnutrition.network.response.FoodModel
import com.agelousis.foodnutrition.network.response.INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
import com.agelousis.foodnutrition.network.response.MeasureModel
import com.agelousis.foodnutrition.theme.AppTheme
import com.agelousis.foodnutrition.theme.Steel
import com.agelousis.foodnutrition.utils.SystemAppearance

@Composable
fun FoodDetailsScreenView(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel
) {
    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
    val (foodColor, setFoodColor) = remember {
        mutableStateOf(
            value = Steel
        )
    }
    val lazyListState = rememberLazyListState()
    val headerImageIsNotVisible by remember {
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
    )
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
                    viewModel.currentIngredientsDataResponseModelState?.FoodImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                height = 300.dp
                            )
                            .animateItem(),
                        color = setFoodColor
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
                    foodColor = foodColor
                )
            }
            //endregion
        }
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
            }
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
            }
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
            }
        )
    }
}
