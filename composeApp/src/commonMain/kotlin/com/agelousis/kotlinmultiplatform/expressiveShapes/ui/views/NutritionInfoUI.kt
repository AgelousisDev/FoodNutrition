package com.agelousis.kotlinmultiplatform.expressiveShapes.ui.views

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agelousis.kotlinmultiplatform.network.enumerations.NutrientType
import com.agelousis.kotlinmultiplatform.network.response.INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.utils.format
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_amount_per_serving_label
import kotlinmultiplatform.composeapp.generated.resources.key_calories_label
import kotlinmultiplatform.composeapp.generated.resources.key_daily_value_label
import kotlinmultiplatform.composeapp.generated.resources.key_nutrition_facts_label
import kotlinmultiplatform.composeapp.generated.resources.key_serving_size_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun NutritionInfoView(
    modifier: Modifier = Modifier,
    ingredientsDataResponseModel: IngredientsDataResponseModel
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            size = 16.dp
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(
                    all = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                space = 8.dp
            )
        ) {
            Text(
                text = stringResource(
                    resource = Res.string.key_nutrition_facts_label
                ),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            HorizontalDivider()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .align(
                            alignment = Alignment.CenterStart
                        ),
                    text = stringResource(
                        resource = Res.string.key_serving_size_label
                    ),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier
                        .align(
                            alignment = Alignment.CenterEnd
                        ),
                    text = "${ingredientsDataResponseModel.totalWeight?.toInt()}g",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            size = 4.dp
                        )
                    ),
                thickness = 8.dp
            )
            Text(
                text = stringResource(
                    resource = Res.string.key_amount_per_serving_label
                ),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .align(
                            alignment = Alignment.CenterStart
                        ),
                    text = stringResource(
                        resource = Res.string.key_calories_label
                    ),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier
                        .align(
                            alignment = Alignment.CenterEnd
                        ),
                    text = ingredientsDataResponseModel.nutrientInfoModelList.firstOrNull { nutritionInfoModelPair ->
                        nutritionInfoModelPair.first == NutrientType.ENERGY_KCAL
                    }?.second?.let { nutrientInfoModel ->
                        nutrientInfoModel.quantity?.toInt()?.toString()
                            ?: return@let null
                    } ?: "",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            size = 4.dp
                        )
                    ),
                thickness = 4.dp
            )
            Text(
                modifier = Modifier
                    .align(
                        alignment = Alignment.End
                    ),
                text = stringResource(
                    resource  = Res.string.key_daily_value_label
                ),
                style = MaterialTheme.typography.labelMedium
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    space = 4.dp
                )
            ) {
                for ((index, nutritionInfoModelPair) in (ingredientsDataResponseModel.nutrientInfoModelList.withIndex())) {
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(
                                horizontal = if (nutritionInfoModelPair.first.hasPadding) 16.dp else 0.dp
                            )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 8.dp
                        )
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    horizontal = if (nutritionInfoModelPair.first.hasPadding) 16.dp else 0.dp
                                )
                                .basicMarquee(),
                            text = nutritionInfoModelPair.first.label,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        val (quantity, unit) = remember {
                            val quantity = (nutritionInfoModelPair.second?.quantity ?: 0.0) format 1
                            quantity to (nutritionInfoModelPair.second?.unit ?: "")
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(
                                    fraction = 1f
                                )
                                .basicMarquee(),
                            text = "$quantity${if (index == 0) " " else ""}$unit",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NutritionInfoViewPreview() {
    MaterialTheme {
        NutritionInfoView(
            modifier = Modifier
                .fillMaxSize(),
            ingredientsDataResponseModel = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
                ?: return@MaterialTheme
        )
    }
}