package com.agelousis.kotlinmultiplatform.network.request

import com.agelousis.kotlinmultiplatform.network.models.IngredientModel
import kotlinx.serialization.Serializable

@Serializable
data class IngredientsDataRequestModel(
     val ingredients: List<IngredientModel>
)