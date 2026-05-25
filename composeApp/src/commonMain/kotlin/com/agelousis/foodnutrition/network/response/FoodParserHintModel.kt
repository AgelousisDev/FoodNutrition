package com.agelousis.foodnutrition.network.response

import kotlinx.serialization.Serializable

@Serializable
data class FoodParserHintModel(
    val food: FoodModel? = null,
    val measures: List<MeasureModel>? = null
)