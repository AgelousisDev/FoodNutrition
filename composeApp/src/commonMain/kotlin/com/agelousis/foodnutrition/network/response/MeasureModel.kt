package com.agelousis.foodnutrition.network.response

import kotlinx.serialization.Serializable

@Serializable
data class MeasureModel(
    val uri: String? = null,
    val label: String? = null,
    val weight: Double? = null
)