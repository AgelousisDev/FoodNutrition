package com.agelousis.kotlinmultiplatform.foodNutrition.ui.views

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
import androidx.compose.material3.SuggestionChipDefaults
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
import com.agelousis.kotlinmultiplatform.network.response.FoodModel
import com.agelousis.kotlinmultiplatform.network.response.INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.network.response.MeasureModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import com.agelousis.kotlinmultiplatform.theme.Steel

@Composable
fun FoodInfoView(
    modifier: Modifier = Modifier,
    ingredientsDataResponseModel: IngredientsDataResponseModel,
    foodColor: Color = Steel,
    headerAlpha: Float = 1f
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
                text = ingredientsDataResponseModel.modelFood?.label
                    ?: "",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )
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
                    text = ingredientsDataResponseModel.modelFood?.category
                        ?: "",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray
                    )
                )
            }
            //region Common measures
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                        space = 12.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    space = 4.dp
                )
            ) {
                ingredientsDataResponseModel.commonMeasures?.forEachIndexed { index, measure ->
                    ServingSizeChip(
                        text = measure,
                        isSelected = index == 0,
                        onClick = {

                        }
                    )
                }
            }
            //endregion
            //region Nutrition
            NutritionInfoView(
                modifier = Modifier
                    .wrapContentHeight(),
                ingredientsDataResponseModel = ingredientsDataResponseModel
            )
            //endregion
            //region Health Labels
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 4.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                (ingredientsDataResponseModel healthLabelList locale).forEach { healthLabel ->
                    ExpressiveHealthLabel(
                        text = healthLabel,
                        backgroundColor = foodColor
                    )
                }
            }
            //endregion
        }
    }
}

@Composable
private fun ExpressiveHealthLabel(
    text: String,
    backgroundColor: Color
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
        shape = expressiveShape,
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = backgroundColor.copy(
                alpha = 0.2f
            ),
            labelColor = backgroundColor
        )
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

@Preview(heightDp = 1600)
@Composable
fun FoodInfoViewPreview() {
    AppTheme {
        FoodInfoView(
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
