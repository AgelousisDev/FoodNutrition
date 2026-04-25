package com.agelousis.kotlinmultiplatform.foodNutrition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.ui.FoodNutritionBaseActivityView

class FoodNutritionBaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FoodNutritionBaseActivityView(
                    viewModel = viewModel(),
                    onBackPress = ::finish
                )
            }
        }
    }
}