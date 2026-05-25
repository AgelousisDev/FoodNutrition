package com.agelousis.foodnutrition.network.response

import kotlinx.serialization.Serializable

@Serializable
data class FoodModel(
    val brand: String? = null,
    val category: String? = null,
    val categoryLabel: String? = null,
    val foodContentsLabel: String? = null,
    val image: String? = null,
    val foodId: String? = null,
    val knownAs: String? = null,
    var label: String? = null,
    val nutrients: NutrientModel? = null,
    val servingSizes: List<ServingSizeModel>? = null,
    val quantity: Double? = null,
    val measure: String? = null,
     val food: String? = null,
    val weight: Double? = null,
    val retainedWeight: Double? = null,
    val servingsPerContainer: Double? = null,
    val measureUri: String? = null,
    val status: String? = null
)