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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.agelousis.kotlinmultiplatform.utils.rememberShareManager
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_copied_clipboard_label
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

@Composable
fun FoodDetailsScreenView(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel,
    ingredientsDataResponseModel: IngredientsDataResponseModel
) {
    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
    val scope = rememberCoroutineScope()
    val shareManager = rememberShareManager()
    val (foodColor, setFoodColor) = remember {
        mutableStateOf(
            value = Steel
        )
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
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
                //region Top Bar Icons
                IconButton(
                    modifier = Modifier
                        .align(
                            alignment = Alignment.TopEnd
                        ),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White
                    ),
                    onClick = {
                        scope.launch {
                            shareManager.share(
                                text = ingredientsDataResponseModel.shareableDetails(),
                                completion = {
                                    scope.launch {
                                        viewModel.snackBarMessage = getString(
                                            resource = Res.string.key_copied_clipboard_label
                                        )
                                    }
                                }
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = Icons.Outlined.Share.name
                    )
                }
                //endregion
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
                foodColor = foodColor
            )
        }
        //endregion
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
