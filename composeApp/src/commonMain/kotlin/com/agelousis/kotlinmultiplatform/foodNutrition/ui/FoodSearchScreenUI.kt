package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.theme.Butterscotch
import com.agelousis.kotlinmultiplatform.foodNutrition.models.RecentSearchModel
import com.agelousis.kotlinmultiplatform.foodNutrition.models.RecommendationModel
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.foodData
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.requestFoodNutrition
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import com.agelousis.kotlinmultiplatform.theme.WhiteTwo
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FoodSearchScreenView(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel,
    foodDetailsRedirection: SuccessBlock<IngredientsDataResponseModel>
) {
    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
    val isOnPreview = LocalInspectionMode.current
    val windowInfo = LocalWindowInfo.current
    val isLandscape = windowInfo.containerSize.width > windowInfo.containerSize.height
    val loaderState by viewModel.showLoaderStateFlow.collectAsState()
    val (foodNameState, searchFood) = remember {
        mutableStateOf(
            value = ""
        )
    }
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(
            count =
                if (isLandscape)
                    3
                else
                    1
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = 24.dp
        ),
        contentPadding = PaddingValues(
            start = 14.dp,
            top = 24.dp,
            end = 24.dp,
            bottom = if (isOnPreview) 24.dp else navigationBarsPadding.calculateBottomPadding()
        )
    ) {
        //region Search label
        item {
            Text(
                modifier = Modifier
                    .animateItem(),
                text = stringResource(
                    resource = Res.string.key_search_label
                ),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
            )
        }
        //endregion
        //region Search Field
        item {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        height = 56.dp
                    )
                    .animateItem(),
                value = foodNameState,
                onValueChange = searchFood,
                placeholder = {
                    Text(
                        text = stringResource(
                            resource = Res.string.key_search_label
                        ),
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
                    if (loaderState)
                        CircularWavyProgressIndicator(
                            modifier = Modifier
                                .size(
                                    size = 32.dp
                                )
                        )
                    else
                        IconButton(
                            enabled = foodNameState.isNotEmpty(),
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
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true
            )
        }
        //endregion
        //region Recent Search Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(),
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
        }
        //endregion
        //region Recent Search List
        item {
            LazyRow(
                modifier = Modifier
                    .animateItem(),
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
        }
        //endregion
        //region Recommendations Header
        item {
            Text(
                modifier = Modifier
                    .animateItem(),
                text = stringResource(
                    resource = Res.string.key_recommend_for_you_label
                ),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        //endregion
        //region Recommendations List
        item {
            Column(
                modifier = Modifier
                    .animateItem(),
                verticalArrangement = Arrangement
                    .spacedBy(
                        space = 16.dp
                    ),
            ) {
                recommendationList.forEach { recommendationModel ->
                    recommendationModel View Modifier
                        .animateItem()
                }
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
    AppTheme {
        FoodSearchScreenView(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        size = 16.dp
                    )
                ),
            viewModel = viewModel {
                FoodNutritionBaseViewModel(
                    dataStore = null
                )
            },
            foodDetailsRedirection = {}
        )
    }
}

@Preview(widthDp = 1200, heightDp = 800)
@Composable
fun FoodSearchScreenViewInLandscapePreview() {
    AppTheme {
        FoodSearchScreenView(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = WhiteTwo,
                    shape = RoundedCornerShape(
                        size = 16.dp
                    )
                ),
            viewModel = viewModel {
                FoodNutritionBaseViewModel(
                    dataStore = null
                )
            },
            foodDetailsRedirection = {}
        )
    }
}
