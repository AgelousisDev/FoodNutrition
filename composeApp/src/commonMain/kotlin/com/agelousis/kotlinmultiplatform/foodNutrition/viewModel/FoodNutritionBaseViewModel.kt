package com.agelousis.kotlinmultiplatform.foodNutrition.viewModel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.agelousis.kotlinmultiplatform.compose.viewModel.UIComposeViewModel

class FoodNutritionBaseViewModel(
    private val dataStore: DataStore<Preferences>
): UIComposeViewModel() {

}