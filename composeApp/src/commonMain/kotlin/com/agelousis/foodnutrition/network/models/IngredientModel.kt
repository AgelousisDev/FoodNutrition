package com.agelousis.foodnutrition.network.models

import com.agelousis.foodnutrition.network.response.FoodModel

import kotlinx.serialization.Serializable

@Serializable
data class IngredientModel(
    val quantity: Int? = null,
    val measureURI: String? = null,
    val qualifiers: List<String>? = null,
    val foodId: String? = null,
    val parsed: List<FoodModel>? = null
)