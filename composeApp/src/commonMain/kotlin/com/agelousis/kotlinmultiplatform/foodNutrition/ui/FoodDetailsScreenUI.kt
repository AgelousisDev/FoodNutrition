package com.agelousis.kotlinmultiplatform.foodNutrition.ui

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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.ui.views.FoodInfoView
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.network.response.FoodModel
import com.agelousis.kotlinmultiplatform.network.response.INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.network.response.MeasureModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import com.agelousis.kotlinmultiplatform.theme.Steel

@Composable
fun FoodDetailsScreenView(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel,
    ingredientsDataResponseModel: IngredientsDataResponseModel
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
                    ingredientsDataResponseModel.Image(
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
                    ingredientsDataResponseModel = ingredientsDataResponseModel,
                    foodColor = foodColor,
                    headerAlpha = headerAlpha
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
    val density = LocalDensity.current
    // The exact scroll distance where the top of FoodInfoView hits the top of the screen
    val scrollThreshold = with(
        receiver = density
    ) {
        (400.dp - 32.dp).toPx()
    }
    val fadeRange = with(
        receiver = density
    ) {
        100.dp.toPx()
    }

    val headerAlpha by remember {
        derivedStateOf {
            if (lazyListState.firstVisibleItemIndex >= 1) {
                1f
            } else {
                val scrollOffset = lazyListState.firstVisibleItemScrollOffset.toFloat()
                val progress = ((scrollOffset - (scrollThreshold - fadeRange)) / fadeRange).coerceIn(
                    minimumValue = 0f,
                    maximumValue = 1f
                )
                progress
            }
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

@Preview(heightDp = 2000)
@Composable
fun FoodDetailsScreenViewPreview() {
    AppTheme {
        FoodDetailsScreenView(
            viewModel = viewModel {
                FoodNutritionBaseViewModel(
                    dataStore = null
                )
            },
            ingredientsDataResponseModel = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL?.copy(
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
            ) ?: return@AppTheme
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
                )
            },
            ingredientsDataResponseModel = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL?.copy(
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
            ) ?: return@AppTheme
        )
    }
}
