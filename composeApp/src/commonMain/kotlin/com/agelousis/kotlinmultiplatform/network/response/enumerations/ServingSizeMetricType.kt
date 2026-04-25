package com.agelousis.kotlinmultiplatform.network.response.enumerations

import kotlinx.serialization.SerialName

enum class ServingSizeMetricType(
    val value: String
) {
    @SerialName(value = "Gram")
    GRAM(
        value = "Gram"
    ),
    @SerialName(value = "Ounce")
    OUNCE(
        value = "Ounce"
    ),
    @SerialName(value = "Cup")
    CUP(
        value = "Cup"
    ),
    @SerialName(value = "Tablespoon")
    TABLESPOON(
        value = "Tablespoon"
    ),
    @SerialName(value = "Teaspoon")
    TEASPOON(
        value = "Teaspoon"
    ),
    @SerialName(value = "Piece")
    PIECE(
        value = "Piece"
    ),
    @SerialName(value = "Milliliter")
    MILLILITER(
        value = "Milliliter"
    ),
    @SerialName(value = "Bagel")
    BAGEL(
        value = "Bagel"
    ),
    @SerialName(value = "Sandwich")
    SANDWICH(
        value = "Sandwich"
    ),
    @SerialName(value = "Fluid ounce")
    FLUID_OUNCE(
        value = "Fluid ounce"
    ),
    @SerialName(value = "Bar")
    BAR(
        value = "Bar"
    )
}