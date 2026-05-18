package com.agelousis.kotlinmultiplatform.foodNutrition.ui.views

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import com.agelousis.kotlinmultiplatform.utils.SuccessBlock
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_food_nutrition_screen_titles
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FoodSearchTextField(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel,
    foodName: String,
    searchFood: SuccessBlock<String>,
    foodDetailsRedirection: SuccessBlock<IngredientsDataResponseModel>
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember {
        FocusRequester()
    }
    TextField(
        modifier = modifier,
        value = foodName,
        onValueChange = searchFood,
        placeholder = {
            Text(
                text = stringArrayResource(
                    resource = Res.array.key_food_nutrition_screen_titles
                )[0],
                color = Color.Gray
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {
                    focusRequester.requestFocus()
                    keyboardController?.show()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = Icons.Outlined.Search.name
                )
            }
        },
        trailingIcon = {
            IconButton(
                enabled = foodName.isNotEmpty(),
                onClick = {
                    viewModel.requestFoodNutrition(
                        foodName = foodName,
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
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                if (foodName.isNotEmpty())
                    viewModel.requestFoodNutrition(
                        foodName = foodName,
                        successBlock = foodDetailsRedirection
                    )
            }
        )
    )
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
            foodName = "Avocado",
            searchFood = {},
            foodDetailsRedirection = {}
        )
    }
}