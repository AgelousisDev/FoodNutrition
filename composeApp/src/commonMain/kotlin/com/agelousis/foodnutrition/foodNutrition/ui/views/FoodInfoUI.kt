package com.agelousis.foodnutrition.foodNutrition.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.foodnutrition.foodNutrition.viewModel.requestFoodNutrition
import com.agelousis.foodnutrition.network.response.FoodModel
import com.agelousis.foodnutrition.network.response.INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
import com.agelousis.foodnutrition.network.response.MeasureModel
import com.agelousis.foodnutrition.theme.AppTheme
import com.agelousis.foodnutrition.theme.Steel
import com.agelousis.foodnutrition.utils.format

@Composable
fun FoodInfoView(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel,
    headerAlpha: Float = 0f,
    foodColor: Color = Steel
) {
    val locale = LocalLocale.current
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(
                    all = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp
            )
        ) {
            Text(
                modifier = Modifier
                    .alpha(
                        alpha = 1f - headerAlpha
                    ),
                text = viewModel.currentIngredientsDataResponseModelState?.modelFood?.label
                    ?: "",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )
            if (viewModel.currentIngredientsDataResponseModelState != null)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 8.dp
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .size(
                                size = 16.dp
                            )
                            .background(
                                color = foodColor,
                                shape = CircleShape
                            )
                    )
                    Text(
                        text = viewModel.currentIngredientsDataResponseModelState?.modelFood?.category
                            ?: "",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Gray
                        )
                    )
                }
            //region Common measures
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 4.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                viewModel.currentIngredientsDataResponseModelState?.commonMeasures?.fastForEach { measure ->
                    val label = "${measure.label}: ${measure.weight?.format(decimals = 0)}g"
                    ServingSizeChip(
                        text = label,
                        isSelected =
                            viewModel.currentIngredientsDataResponseModelState?.ingredients?.firstOrNull()?.parsed?.firstOrNull()?.quantity?.toInt() == measure.weight?.toInt(),
                        onClick = {
                            if (viewModel.currentIngredientsDataResponseModelState?.ingredients?.firstOrNull()?.parsed?.firstOrNull()?.quantity?.toInt() == measure.weight?.toInt())
                                return@ServingSizeChip
                            requestFoodNutrition(
                                viewModel = viewModel,
                                product = viewModel.currentIngredientsDataResponseModelState?.modelFood?.label
                                    ?: return@ServingSizeChip,
                                quantity = measure.weight?.toInt()
                                    ?: return@ServingSizeChip
                            )
                        }
                    )
                }
            }
            //endregion
            //region Nutrition
            viewModel.currentIngredientsDataResponseModelState?.let {
                NutritionInfoView(
                    modifier = Modifier
                        .wrapContentHeight(),
                    ingredientsDataResponseModel = it
                )
            }
            //endregion
            //region Health Labels
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 4.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                viewModel.currentIngredientsDataResponseModelState?.healthLabelList(
                    locale = locale
                )?.forEach { healthLabel ->
                    ExpressiveHealthLabel(
                        text = healthLabel
                    )
                }
            }
            //endregion
        }
    }
}

@Composable
private fun ExpressiveHealthLabel(
    text: String
) {
    // Material Expressive uses asymmetric or "squircle" shapes
    val expressiveShape = RoundedCornerShape(
        topStart = 16.dp,
        bottomEnd = 16.dp,
        topEnd = 4.dp,
        bottomStart = 4.dp
    )

    SuggestionChip(
        onClick = { },
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        },
        shape = expressiveShape
    )
}

@Composable
private fun ServingSizeChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = text
            )
        },
        shape = CircleShape,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

private fun requestFoodNutrition(
    viewModel: FoodNutritionBaseViewModel,
    product: String,
    quantity: Int
) {
    viewModel.requestFoodNutrition(
        product = product.lowercase(),
        quantity = quantity
    )
}

@Preview(heightDp = 1600)
@Composable
fun FoodInfoViewPreview() {
    AppTheme {
        FoodInfoView(
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
