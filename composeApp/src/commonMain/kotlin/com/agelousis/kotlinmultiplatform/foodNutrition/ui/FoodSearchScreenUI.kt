package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.agelousis.kotlinmultiplatform.foodNutrition.ui.views.FoodSearchTextField
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.RECENT_SEARCH_KEY
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.clearRecentSearch
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import com.agelousis.kotlinmultiplatform.theme.WhiteTwo
import com.agelousis.kotlinmultiplatform.utils.SuccessBlock
import com.agelousis.kotlinmultiplatform.utils.getModels
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_clear_all_label
import kotlinmultiplatform.composeapp.generated.resources.key_recent_search_label
import kotlinmultiplatform.composeapp.generated.resources.key_search_label
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.resources.stringResource

/*private val recommendationList = listOf(
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
)*/

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FoodSearchScreenView(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel,
    defaultRecentSearchList: List<RecentSearchModel>? = null,
    foodDetailsRedirection: SuccessBlock<IngredientsDataResponseModel>
) {
    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
    val isOnPreview = LocalInspectionMode.current
    val windowInfo = LocalWindowInfo.current
    val isLandscape = windowInfo.containerSize.width > windowInfo.containerSize.height
    val (foodNameState, searchFood) = remember {
        mutableStateOf(
            value = ""
        )
    }
    //region Recent search
    val recentSearches by remember(
        key1 = viewModel.dataStore
    ) {
        viewModel.dataStore?.getModels<RecentSearchModel>(
            key = RECENT_SEARCH_KEY
        )
            ?: flowOf(
                value = defaultRecentSearchList
                    ?: emptyList()
            )
    }.collectAsState(
        initial = defaultRecentSearchList
            ?: emptyList()
    )
    //endregion
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(
            count =
                if (isLandscape)
                    2
                else
                    1
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp
        ),
        contentPadding = PaddingValues(
            start = 24.dp,
            top = 24.dp,
            end = 24.dp,
            bottom = if (isOnPreview) 24.dp else navigationBarsPadding.calculateBottomPadding()
        )
    ) LazyGridScope@ {
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
            FoodSearchTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        height = 56.dp
                    )
                    .animateItem(),
                viewModel = viewModel,
                foodName = foodNameState,
                searchFood = searchFood,
                foodDetailsRedirection = foodDetailsRedirection
            )
        }
        //endregion
        //region Recent search label
        if (recentSearches.isNotEmpty())
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
                            viewModel.clearRecentSearch()
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
        if (recentSearches.isNotEmpty())
            item {
                RecentSearchItems(
                    modifier = Modifier
                        .animateItem(),
                    lazyGridScope = this@LazyGridScope,
                    viewModel = viewModel,
                    recentSearches = recentSearches,
                    foodDetailsRedirection = foodDetailsRedirection
                )
            }
        //endregion
        //region Recommendations Header
        /*item {
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
        }*/
        //endregion
        //region Recommendations List
        /*item {
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
        }*/
        //endregion
    }
}

@Composable
private fun RecentSearchItems(
    modifier: Modifier,
    lazyGridScope: LazyGridScope,
    viewModel: FoodNutritionBaseViewModel,
    recentSearches: List<RecentSearchModel>,
    foodDetailsRedirection: SuccessBlock<IngredientsDataResponseModel>
) {
    val windowInfo = LocalWindowInfo.current
    val isLandscape = windowInfo.containerSize.width > windowInfo.containerSize.height
    val screenWidth = LocalWindowInfo.current.containerDpSize.width
    if (!isLandscape)
        FlowRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            ),
            maxItemsInEachRow =
                if (isLandscape)
                    5
                else
                    2
        ) {
            recentSearches.forEach { recentSearchModel ->
                recentSearchModel.View(
                    modifier = Modifier
                        .width(
                            width = (screenWidth / 2) - 32.dp
                        ),
                    recentSearch = RecentSearchModel@ {
                        viewModel.requestFoodNutrition(
                            foodName = this@RecentSearchModel.title.lowercase(),
                            successBlock = foodDetailsRedirection
                        )
                    }
                )
            }
        }
    else
        lazyGridScope.apply {
            items(
                items = recentSearches
            ) { recentSearchModel ->
                recentSearchModel.View(
                    modifier = Modifier
                        .width(
                            width = (screenWidth / 2) - 32.dp
                        ),
                    recentSearch = RecentSearchModel@ {
                        viewModel.requestFoodNutrition(
                            foodName = this@RecentSearchModel.title.lowercase(),
                            successBlock = foodDetailsRedirection
                        )
                    }
                )
            }
        }
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
            defaultRecentSearchList = listOf(
                RecentSearchModel(
                    title = "Andy & Cindy's Diner",
                    label = "22 Powlowski Plains"
                ),
                RecentSearchModel(
                    title = "Gado & Grill",
                    label = "78 Schultz Cape Apt. 132"
                )
            ),
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
            defaultRecentSearchList = listOf(
                RecentSearchModel(
                    title = "Andy & Cindy's Diner",
                    label = "22 Powlowski Plains"
                ),
                RecentSearchModel(
                    title = "Gado & Grill",
                    label = "78 Schultz Cape Apt. 132"
                )
            ),
            foodDetailsRedirection = {}
        )
    }
}
