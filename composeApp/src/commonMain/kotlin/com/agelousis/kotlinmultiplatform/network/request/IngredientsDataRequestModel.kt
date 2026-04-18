package com.agelousis.kotlinmultiplatform.network.request

import com.agelousis.kotlinmultiplatform.network.models.IngredientModel
data class IngredientsDataRequestModel(
     val ingredients: List<IngredientModel>
)