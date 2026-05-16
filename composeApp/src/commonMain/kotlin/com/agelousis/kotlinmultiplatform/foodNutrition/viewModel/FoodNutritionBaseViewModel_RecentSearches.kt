package com.agelousis.kotlinmultiplatform.foodNutrition.viewModel

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.agelousis.kotlinmultiplatform.foodNutrition.models.RecentSearchModel
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.utils.addModel
import com.agelousis.kotlinmultiplatform.utils.removeModels
import kotlinx.coroutines.launch

val RECENT_SEARCH_KEY = stringPreferencesKey(
    name = "recentSearch"
)

infix fun FoodNutritionBaseViewModel.saveRecentSearch(
    ingredientsDataResponseModel: IngredientsDataResponseModel?
) {
    viewModelScope.launch {
        dataStore?.addModel(
            key = RECENT_SEARCH_KEY,
            model = RecentSearchModel(
                title = ingredientsDataResponseModel?.modelFood?.label
                    ?: "",
                label = ingredientsDataResponseModel?.modelFood?.category
                    ?: "",
                imageUrl = ingredientsDataResponseModel?.modelFood?.image
            )
        )
    }
}

fun FoodNutritionBaseViewModel.clearRecentSearch() {
    viewModelScope.launch {
        dataStore?.removeModels<RecentSearchModel>(
            key = RECENT_SEARCH_KEY
        )
    }
}