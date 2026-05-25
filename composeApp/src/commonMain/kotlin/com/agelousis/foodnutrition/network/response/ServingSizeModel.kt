package com.agelousis.foodnutrition.network.response

import kotlinx.serialization.Serializable

@Serializable
data class ServingSizeModel(
    val uri: String?,
    val label: String?,
    val quantity: Double?
)