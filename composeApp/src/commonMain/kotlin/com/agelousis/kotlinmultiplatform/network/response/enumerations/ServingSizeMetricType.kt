package com.agelousis.kotlinmultiplatform.network.response.enumerations

import kotlinx.serialization.SerialName

enum class ServingSizeMetricType {
    @SerialName(value = "Gram")
    GRAM,
    @SerialName(value = "Ounce")
    OUNCE,
    @SerialName(value = "Cup")
    CUP
}