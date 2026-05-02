package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel

@Composable
fun FoodSearchScreenView(
    modifier: Modifier = Modifier,
    viewModel: FoodNutritionBaseViewModel
) {

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
            viewModel = viewModel()
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
            viewModel = viewModel()
        )
    }
}