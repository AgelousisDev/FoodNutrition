package com.agelousis.foodnutrition.network.request

import com.agelousis.foodnutrition.network.models.IngredientModel
import kotlinx.serialization.Serializable

@Serializable
data class IngredientsDataRequestModel(
     val ingredients: List<IngredientModel>
)