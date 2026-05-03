package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.compose.theme.Butterscotch
import com.agelousis.kotlinmultiplatform.foodNutrition.models.RecentSearchModel
import com.agelousis.kotlinmultiplatform.foodNutrition.models.RecommendationModel
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.foodData
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.requestFoodNutrition
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.utils.SuccessBlock
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_clear_all_label
import kotlinmultiplatform.composeapp.generated.resources.key_recent_search_label
import kotlinmultiplatform.composeapp.generated.resources.key_recommend_for_you_label
import kotlinmultiplatform.composeapp.generated.resources.key_search_label
import org.jetbrains.compose.resources.stringResource

private val recentSearchList = listOf(
    RecentSearchModel(
        title = "Andy & Cindy's Diner",
        address = "22 Powlowski Plains"
    ),
    RecentSearchModel(
        title = "Gado & Grill",
        address = "78 Schultz Cape Apt. 132"
    )
)

private val recommendationList = listOf(
    RecommendationModel(
        title = "Kellys Cafe and Espresso",
        address = "882 Swift Courts Apt. 918",
        rating = 4.8,
        reviewsCount = 233
    ),
    RecommendationModel(
        title = "Panda Inn Mongolian Bar",
        address = "441 Bria Flat Apt. 620",
        rating = 4.8,
        reviewsCount = 233
    ),
    RecommendationModel(
        title = "Juanito's Taqueria",
        address = "478 Konopelski Union Apt. 506",
        rating = 4.8,
        reviewsCount = 233
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodSearchScreenView(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel,
    foodDetailsRedirection: SuccessBlock<IngredientsDataResponseModel>
) {
    val (foodNameState, searchFood) = remember {
        mutableStateOf(
            value = ""
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
            .padding(
                horizontal = 16.dp
            )
    ) {
        Spacer(
            modifier = Modifier
                .height(
                    height = 24.dp
                )
        )

        Text(
            text = stringResource(
                resource = Res.string.key_search_label
            ),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
        )

        Spacer(
            modifier = Modifier
                .height(
                    height = 16.dp
                )
        )

        //region Search Field
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    height = 56.dp
                ),
            value = foodNameState,
            onValueChange = searchFood,
            placeholder = {
                Text(
                    text = stringResource(
                        resource = Res.string.key_search_label),
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = Icons.Outlined.Search.name
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        requestFoodNutrition(
                            viewModel = viewModel,
                            foodName = foodNameState,
                            successBlock = foodDetailsRedirection
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = Icons.AutoMirrored.Outlined.KeyboardArrowRight.name
                    )
                }
            },
            shape = RoundedCornerShape(
                size = 12.dp
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = Color(0xFFF5F5F5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
        //endregion

        Spacer(
            modifier = Modifier
                .height(
                    height = 24.dp
                )
        )

        //region Recent Search Header
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(
                    resource = Res.string.key_recent_search_label
                ),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            TextButton(
                onClick = {

                }
            ) {
                Text(
                    text = stringResource(
                        resource = Res.string.key_clear_all_label
                    ),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Butterscotch,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
        //endregion

        Spacer(
            modifier = Modifier
                .height(
                    height = 16.dp
                )
        )

        //region Recent Search List
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                space = 16.dp
            ),
            contentPadding = PaddingValues(
                bottom = 8.dp
            )
        ) {
            items(
                items = recentSearchList
            ) { recentSearchModel ->
                recentSearchModel View Modifier
                    .animateItem()
            }
        }
        //endregion

        Spacer(
            modifier = Modifier
                .height(
                    height = 24.dp
                )
        )

        //region Recommendations Header
        Text(
            text = stringResource(
                resource = Res.string.key_recommend_for_you_label
            ),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        //endregion

        Spacer(
            modifier = Modifier.height(
                height = 16.dp
            )
        )

        //region Recommendations List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(
                items = recommendationList
            ) { recommendationModel ->
                recommendationModel View Modifier
                    .animateItem()
            }
        }
        //endregion
    }
}

private fun requestFoodNutrition(
    viewModel: FoodNutritionBaseViewModel,
    foodName: String,
    successBlock: SuccessBlock<IngredientsDataResponseModel>
) {
    (viewModel foodData foodName)?.let(
        block = successBlock
    ) ?: viewModel.requestFoodNutrition(
        product = foodName,
        successBlock = successBlock
    )

}

@Preview
@Composable
fun FoodSearchScreenViewPreview() {
    MaterialTheme {
        FoodSearchScreenView(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        size = 16.dp
                    )
                ),
            viewModel = viewModel(),
            foodDetailsRedirection = {}
        )
    }
}

@Preview(widthDp = 1200, heightDp = 800)
@Composable
fun FoodSearchScreenViewInLandscapePreview() {
    MaterialTheme {
        FoodSearchScreenView(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        size = 16.dp
                    )
                ),
            viewModel = viewModel(),
            foodDetailsRedirection = {}
        )
    }
}
