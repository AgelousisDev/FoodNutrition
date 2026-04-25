package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.compose.views.ScalingHorizontalPagerView
import com.agelousis.kotlinmultiplatform.foodNutrition.enumerations.KetogenicSuperFood
import com.agelousis.kotlinmultiplatform.foodNutrition.ui.views.NutritionInfoView
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.foodData
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.foodDataStateMap
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.requestFoodNutrition
import com.agelousis.kotlinmultiplatform.network.response.INGREDIENTS_DATA_RESPONSE_MOCK_MODEL

@Composable
fun KetogenicSuperFoodsScreenView(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel
) {
    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
    val isOnPreview = LocalInspectionMode.current
    val (selectedKetogenicSuperFood, setKetogenicSuperFood) = remember {
        mutableStateOf(
            value = KetogenicSuperFood.entries[0]
        )
    }
    //region Request Data
    RequestData(
        viewModel = viewModel,
        selectedKetogenicSuperFood = selectedKetogenicSuperFood
    )
    //endregion
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = 24.dp
        ),
        contentPadding = PaddingValues(
            bottom = if (isOnPreview) 24.dp else navigationBarsPadding.calculateBottomPadding()
        )
    ) {
        //region Ketogenic SuperFoods pager
        item {
            ScalingHorizontalPagerView(
                modifier = Modifier
                    .padding(
                        top = 24.dp
                    )
                    .animateItem(),
                scalingHorizontalPagerDataList = KetogenicSuperFood.entries,
                pagerViewBlock = KetogenicSuperFood@ {
                    setKetogenicSuperFood(
                        this@KetogenicSuperFood as? KetogenicSuperFood
                            ?: return@KetogenicSuperFood
                    )
                }
            )
        }
        //endregion
        //region Food nutrition
        (viewModel foodData selectedKetogenicSuperFood.name.lowercase())?.let { dataResponseModel ->
            item {
                NutritionInfoView(
                    modifier = Modifier
                        .padding(
                            all = 24.dp
                        )
                        .animateItem(),
                    ingredientsDataResponseModel = dataResponseModel
                )
            }
        }
        //endregion
    }
}

@Composable
private fun RequestData(
    viewModel: FoodNutritionBaseViewModel,
    selectedKetogenicSuperFood: KetogenicSuperFood
) {
    LaunchedEffect(
        key1 = selectedKetogenicSuperFood
    ) {
        //request Food nutrition
        if ((viewModel foodData selectedKetogenicSuperFood.name.lowercase()) == null)
            viewModel requestFoodNutrition selectedKetogenicSuperFood.name.lowercase()
        //endregion
    }
}
//endregion

@Preview(heightDp = 1400)
@Composable
fun KetogenicSuperFoodsScreenViewPreview() {
    MaterialTheme {
        KetogenicSuperFoodsScreenView(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        size = 16.dp
                    )
                ),
            viewModel = viewModel<FoodNutritionBaseViewModel>().also { viewModel ->
                viewModel.foodDataStateMap[
                    KetogenicSuperFood.AVOCADO.name.lowercase()
                ] = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
                    ?: return@MaterialTheme
            }
        )
    }
}
