package com.agelousis.kotlinmultiplatform.foodNutrition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.foodNutrition.ui.FoodNutritionBaseActivityView
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import com.agelousis.kotlinmultiplatform.utils.DataStoreProvider

class FoodNutritionBaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val dataStore = remember {
                    DataStoreProvider(
                        context = this
                    ).createDataStore()
                }
                FoodNutritionBaseActivityView(
                    viewModel = viewModel {
                        FoodNutritionBaseViewModel(
                            dataStore = dataStore
                        )
                    },
                    onBackPress = ::finish
                )
            }
        }
    }
}