package com.agelousis.kotlinmultiplatform.network.models

import com.agelousis.kotlinmultiplatform.network.response.FoodModel
data class IngredientModel(
    val quantity: Double? = null,
    val measureUri: String? = null,
    val qualifiers: List<String>? = null,
    val foodId: String? = null,
     val parsed: List<FoodModel>? = null
)