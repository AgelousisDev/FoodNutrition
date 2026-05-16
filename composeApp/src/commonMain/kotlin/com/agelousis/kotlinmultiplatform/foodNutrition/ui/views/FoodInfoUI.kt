package com.agelousis.kotlinmultiplatform.foodNutrition.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agelousis.kotlinmultiplatform.network.response.FoodModel
import com.agelousis.kotlinmultiplatform.network.response.INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.network.response.MeasureModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import com.agelousis.kotlinmultiplatform.theme.Begonia
import com.agelousis.kotlinmultiplatform.theme.Butterscotch
import com.agelousis.kotlinmultiplatform.theme.GraniteGrayColor
import com.agelousis.kotlinmultiplatform.theme.Jasmine
import com.agelousis.kotlinmultiplatform.theme.LightPurple
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_bookmark_label
import kotlinmultiplatform.composeapp.generated.resources.key_photo_label
import kotlinmultiplatform.composeapp.generated.resources.key_ratings_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun FoodInfoView(
    modifier: Modifier = Modifier,
    ingredientsDataResponseModel: IngredientsDataResponseModel
) {
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
                space = 16.dp,
                alignment = Alignment.Top
            )
        ) {
            Text(
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
                            color = GraniteGrayColor,
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
            LazyRow(
                horizontalArrangement = Arrangement
                    .spacedBy(
                        space = 12.dp
                    )
            ) {
                itemsIndexed(
                    items = ingredientsDataResponseModel.commonMeasures
                        ?: listOf()
                ) { index, measure ->
                    BadgeItem(
                        text = measure,
                        backgroundColor =
                            if (index == 0)
                                Butterscotch
                            else
                                Color.Transparent,
                        textColor =
                            if (index == 0)
                                Color.White
                            else
                                Color.Gray,
                    )
                }
            }

            NutritionInfoView(
                modifier = Modifier
                    .wrapContentHeight(),
                ingredientsDataResponseModel = ingredientsDataResponseModel
            )

            //region Stats Row
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DetailStatItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(
                                    size = 32.dp
                                )
                                .background(
                                    color = Jasmine,
                                    shape = CircleShape
                                )
                        )
                    },
                    label = stringResource(
                        resource = Res.string.key_ratings_label
                    ),
                    value = "4.5"
                )
                DetailStatItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(
                                    size = 32.dp
                                )
                                .background(
                                    color = Begonia,
                                    shape = CircleShape
                                )
                        )
                    },
                    label = stringResource(
                        resource = Res.string.key_bookmark_label
                    ),
                    value = "137k"
                )
                DetailStatItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(
                                    size = 32.dp
                                )
                                .background(
                                    color = LightPurple,
                                    shape = CircleShape
                                )
                        )
                    },
                    label = stringResource(
                        resource = Res.string.key_photo_label
                    ),
                    value = "346"
                )
            }

            Text(
                text = "From the French countryside, to your doorstep. PAUL was founded in 1889 as a family bakery and patisserie. Savour a selection of viennoiserie (croissants etc.)...",
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 24.sp,
                    color = Color.DarkGray
                )
            )

            Text(
                text = stringResource(
                    resource = Res.string.key_photo_label
                ),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            LazyRow(
                horizontalArrangement = Arrangement
                    .spacedBy(
                        space = 12.dp
                    ),
                contentPadding = PaddingValues(
                    bottom = 24.dp
                )
            ) {
                items(
                    count = 5
                ) {
                    Image(
                        modifier = Modifier
                            .size(
                                size = 100.dp
                            )
                            .clip(
                                shape = RoundedCornerShape(
                                    size = 16.dp
                                )
                            ),
                        painter = ColorPainter(
                            color = Color.LightGray
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
private fun BadgeItem(
    text: String,
    backgroundColor: Color,
    textColor: Color = Color.White
) {
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(
            size = 8.dp
        ),
        border =
            if (backgroundColor == Color.Transparent)
                BorderStroke(
                    width = 1.dp,
                    color = Color.LightGray
                )
            else
                null
    ) {
        Text(
            modifier = Modifier
                .padding(
                    horizontal = 12.dp,
                    vertical = 6.dp
                ),
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
private fun DetailStatItem(
    icon: @Composable () -> Unit,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        Spacer(
            modifier = Modifier
                .width(
                    width = 8.dp
                )
        )
        Column {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray
                )
            )
        }
    }
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
