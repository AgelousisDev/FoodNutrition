package com.agelousis.kotlinmultiplatform.foodNutrition.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExpandedFullScreenSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.models.RecentSearchModel
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import com.agelousis.kotlinmultiplatform.utils.SuccessBlock
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_food_nutrition_screen_titles
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringArrayResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FoodSearchTextField(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel,
    recentSearches: List<RecentSearchModel> = emptyList(),
    foodDetailsRedirection: SuccessBlock<IngredientsDataResponseModel>
) {
    val searchBarState = rememberSearchBarState()
    val textFieldState = rememberTextFieldState()
    val scope = rememberCoroutineScope()
    val inputField =
        @Composable {
            SearchBarDefaults.InputField(
                textFieldState = textFieldState,
                searchBarState = searchBarState,
                onSearch = {
                    viewModel.requestFoodNutrition(
                        foodName = textFieldState.text.toString(),
                        successBlock = foodDetailsRedirection
                    )
                    textFieldState.setTextAndPlaceCursorAtEnd(
                        text = ""
                    )
                    scope.launch {
                        searchBarState.animateToCollapsed()
                    }
                },
                placeholder = {
                    Text(
                        modifier = Modifier
                            .clearAndSetSemantics {},
                        text = stringArrayResource(
                            resource = Res.array.key_food_nutrition_screen_titles
                        )[0]
                    )
                },
                leadingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.requestFoodNutrition(
                                foodName = textFieldState.text.toString(),
                                successBlock = foodDetailsRedirection
                            )
                            textFieldState.setTextAndPlaceCursorAtEnd(
                                text = ""
                            )
                            scope.launch {
                                searchBarState.animateToCollapsed()
                            }
                        },
                        enabled = textFieldState.text.isNotEmpty()
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
                    }
                },
                trailingIcon = {
                    if (textFieldState.text.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                textFieldState.setTextAndPlaceCursorAtEnd(
                                    text = ""
                                )
                                scope.launch {
                                    searchBarState.animateToCollapsed()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = Icons.Default.Close.name
                            )
                        }
                    }
                }
            )
        }
    SearchBar(
        modifier = modifier,
        state = searchBarState,
        inputField = inputField
    )
    ExpandedFullScreenSearchBar(
        state = searchBarState,
        inputField = inputField
    ) {
        recentSearches.forEach { recentSearch ->
            ListItem(
                modifier = Modifier
                    .clickable {
                        textFieldState.setTextAndPlaceCursorAtEnd(
                            text = recentSearch.title
                        )
                        scope.launch {
                            searchBarState.animateToCollapsed()
                        }
                        viewModel.requestFoodNutrition(
                            foodName = recentSearch.title,
                            successBlock = foodDetailsRedirection
                        )
                    },
                headlineContent = {
                    Text(
                        text = recentSearch.title
                    )
                },
                supportingContent = {
                    Text(
                        text = recentSearch.label
                    )
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = Icons.Default.History.name
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun FoodSearchTextFieldPreview() {
    AppTheme {
        FoodSearchTextField(
            viewModel = viewModel {
                FoodNutritionBaseViewModel(
                    dataStore = null
                )
            },
            foodDetailsRedirection = {}
        )
    }
}