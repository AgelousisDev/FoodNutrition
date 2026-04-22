package com.agelousis.kotlinmultiplatform.network.response

import com.agelousis.kotlinmultiplatform.network.response.enumerations.ServingSizeMetricType

import kotlinx.serialization.Serializable

@Serializable
data class ServingSizeModel(
    val uri: String?,
    val label: ServingSizeMetricType?,
    val quantity: Double?
)