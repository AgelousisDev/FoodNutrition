package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.models.RecentSearchModel
import com.agelousis.kotlinmultiplatform.foodNutrition.ui.alert.ClearRecentSearchAlert
import com.agelousis.kotlinmultiplatform.foodNutrition.ui.views.FoodSearchTextField
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.RECENT_SEARCH_KEY
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.clearRecentSearch
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import com.agelousis.kotlinmultiplatform.utils.SuccessBlock
import com.agelousis.kotlinmultiplatform.utils.SystemAppearance
import com.agelousis.kotlinmultiplatform.utils.getModels
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_clear_all_label
import kotlinmultiplatform.composeapp.generated.resources.key_food_nutrition_screen_titles
import kotlinmultiplatform.composeapp.generated.resources.key_recent_search_label
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource

private const val LANDSCAPE_GRID_COLUMNS = 3

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
    val lazyGridState = rememberLazyGridState()
    val headerAlpha = headerConfiguration(
        lazyGridState = lazyGridState,
        viewModel = viewModel
    )
    SystemAppearance(
        isLight = !isSystemInDarkTheme()
    )
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
    //region Clear recent search alert
    val (clearRecentSearchAlert, showClearRecentSearchAlert) = remember {
        mutableStateOf(
            value = false
        )
    }
    ClearRecentSearchAlert(
        state = clearRecentSearchAlert,
        confirmBlock = {
            showClearRecentSearchAlert(false)
            viewModel.clearRecentSearch()
        },
        cancelButton = {
            showClearRecentSearchAlert(false)
        }
    )
    //endregion
    Surface(
        modifier = modifier
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyGridState,
            columns = GridCells.Fixed(
                count =
                    if (isLandscape)
                        LANDSCAPE_GRID_COLUMNS
                    else
                        1
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(
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
            item(
                span = {
                    GridItemSpan(
                        currentLineSpan =
                            if (isLandscape)
                                LANDSCAPE_GRID_COLUMNS
                            else
                                1
                    )
                }
            ) {
                Text(
                    modifier = Modifier
                        .alpha(
                            alpha = 1f - headerAlpha
                        )
                        .animateItem(),
                    text = stringArrayResource(
                        resource = Res.array.key_food_nutrition_screen_titles
                    )[0],
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                )
            }
            //endregion
            //region Search Field
            item(
                span = {
                    GridItemSpan(
                        currentLineSpan =
                            if (isLandscape)
                                2
                            else
                                1
                    )
                }
            ) {
                FoodSearchTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    viewModel = viewModel,
                    recentSearches = recentSearches,
                    foodDetailsRedirection = foodDetailsRedirection
                )
            }
            //endregion
            //region Recent search label
            if (recentSearches.isNotEmpty())
                item {
                    Row(
                        modifier = Modifier
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
                                showClearRecentSearchAlert(true)
                            }
                        ) {
                            Text(
                                text = stringResource(
                                    resource = Res.string.key_clear_all_label
                                ),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            //endregion
            //region Recent Search List in Portrait
            if (!isLandscape
                && recentSearches.isNotEmpty()
            )
                item {
                    RecentSearchItems(
                        modifier = Modifier
                            .animateItem(),
                        viewModel = viewModel,
                        recentSearches = recentSearches,
                        foodDetailsRedirection = foodDetailsRedirection
                    )
                }
            //endregion
            //region Recent Search List in Landscape
            if (isLandscape)
                items(
                    items = recentSearches
                ) { recentSearchModel ->
                    recentSearchModel.View(
                        modifier = Modifier
                            .fillMaxWidth(),
                        recentSearch = RecentSearchModel@ {
                            viewModel.requestFoodNutrition(
                                foodName = this@RecentSearchModel.title,
                                successBlock = foodDetailsRedirection
                            )
                        }
                    )
                }
            //endregion
        }
    }
}

@Composable
private fun RecentSearchItems(
    modifier: Modifier,
    viewModel: FoodNutritionBaseViewModel,
    recentSearches: List<RecentSearchModel>,
    foodDetailsRedirection: SuccessBlock<IngredientsDataResponseModel>
) {
    val screenWidth = LocalWindowInfo.current.containerDpSize.width
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment =
                if (recentSearches.size.rem(
                        other = 2
                    ) == 1)
                    Alignment.Start
                else
                    Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        maxItemsInEachRow = 2
    ) {
        recentSearches.reversed().forEach { recentSearchModel ->
            recentSearchModel.View(
                modifier = Modifier
                    .width(
                        width = (screenWidth / 2) - 32.dp
                    ),
                recentSearch = RecentSearchModel@ {
                    viewModel.requestFoodNutrition(
                        foodName = this@RecentSearchModel.title,
                        successBlock = foodDetailsRedirection
                    )
                }
            )
        }
    }
}

@Composable
private fun headerConfiguration(
    lazyGridState: LazyGridState,
    viewModel: FoodNutritionBaseViewModel
): Float {
    //region Header Configuration
    val headerAlpha by remember {
        derivedStateOf {
            if (lazyGridState.firstVisibleItemIndex > 0)
                1f
            else
                (lazyGridState.firstVisibleItemScrollOffset / 200f).coerceIn(
                    minimumValue = 0f,
                    maximumValue = 1f
                )
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

@Preview(showBackground = true)
@Composable
fun FoodSearchScreenViewPreview() {
    AppTheme {
        FoodSearchScreenView(
            modifier = Modifier
                .fillMaxSize(),
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

@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
fun FoodSearchScreenViewInLandscapePreview() {
    AppTheme {
        FoodSearchScreenView(
            modifier = Modifier
                .fillMaxSize(),
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

@Preview(showBackground = true)
@Composable
fun FoodSearchScreenViewPreviewDarkMode() {
    AppTheme(
        darkTheme = true
    ) {
        FoodSearchScreenView(
            modifier = Modifier
                .fillMaxSize(),
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
