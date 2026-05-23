package com.agelousis.kotlinmultiplatform.foodNutrition.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.agelousis.kotlinmultiplatform.compose.viewModel.UIComposeViewModel
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.utils.SuccessBlock

class FoodNutritionBaseViewModel(
    val dataStore: DataStore<Preferences>?
): UIComposeViewModel() {

    //region Current product details
    var currentIngredientsDataResponseModelState by mutableStateOf<IngredientsDataResponseModel?>(
        value = null
    )
    //endregion

    //region Request food nutrition
    fun requestFoodNutrition(
        foodName: String,
        successBlock: SuccessBlock<IngredientsDataResponseModel>
    ) {
        (this foodData foodName.lowercase())?.let(
            block = successBlock
        ) ?: requestFoodNutrition(
            product = foodName.lowercase(),
            successBlock = successBlock
        )

    }
    //endregion

}