package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agelousis.kotlinmultiplatform.foodNutrition.ui.views.FoodInfoView
import com.agelousis.kotlinmultiplatform.network.response.INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme

@Composable
fun FoodDetailsScreenView(
    modifier: Modifier = Modifier,
    ingredientsDataResponseModel: IngredientsDataResponseModel
) {
    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
    val pagerState = rememberPagerState {
        1
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = navigationBarsPadding.calculateBottomPadding()
        )
    ) {
        //region Image Slider
        item {
            Box(
                modifier = Modifier

            ) {
                ingredientsDataResponseModel Image Modifier
                    .fillMaxWidth()
                    .height(
                        height = 300.dp
                    )
                    .animateItem()

                //region Top Bar Icons
                IconButton(
                    modifier = Modifier
                        .align(
                            alignment = Alignment.TopEnd
                        )
                        .padding(
                            all = 24.dp
                        ),
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = Icons.Outlined.Share.name
                    )
                }
                //endregion
            }
        }
        //endregion
        //region Restaurant Info Card
        item {
            FoodInfoView(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(
                        y = (-24).dp
                    )
                    .animateItem(),
                ingredientsDataResponseModel = ingredientsDataResponseModel
            )
        }
        //endregion
    }
}

@Preview(heightDp = 2000)
@Composable
fun FoodDetailsScreenViewPreview() {
    AppTheme {
        FoodDetailsScreenView(
            ingredientsDataResponseModel = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
                ?: return@AppTheme
        )
    }
}

@Preview(widthDp = 1200, heightDp = 1800)
@Composable
fun FoodDetailsScreenViewInLandscapePreview() {
    AppTheme {
        FoodDetailsScreenView(
            ingredientsDataResponseModel = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
                ?: return@AppTheme
        )
    }
}
