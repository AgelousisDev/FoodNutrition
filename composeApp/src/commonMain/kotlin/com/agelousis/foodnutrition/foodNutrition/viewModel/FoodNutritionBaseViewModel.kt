package com.agelousis.foodnutrition.foodNutrition.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.agelousis.foodnutrition.compose.viewModel.UIComposeViewModel
import com.agelousis.foodnutrition.network.response.IngredientsDataResponseModel

class FoodNutritionBaseViewModel(
    val dataStore: DataStore<Preferences>?
): UIComposeViewModel() {

    //region Current product details
    var currentIngredientsDataResponseModelState by mutableStateOf<IngredientsDataResponseModel?>(
        value = null
    )
    //endregion

    //region Request food nutrition

    fun clearCurrentFood() {
        currentIngredientsDataResponseModelState = null
    }

    infix fun requestFoodNutrition(
        foodName: String
    ) {
        (this foodData foodName.lowercase())?.let { ingredientsDataResponseModel ->
            currentIngredientsDataResponseModelState = ingredientsDataResponseModel
        } ?: requestFoodNutrition(
            product = foodName.lowercase()
        )

    }
    //endregion

}