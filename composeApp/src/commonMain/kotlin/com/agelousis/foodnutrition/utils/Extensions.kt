package com.agelousis.foodnutrition.utils

import kotlinx.serialization.json.Json
import kotlin.math.pow
import kotlin.math.round

val jsonWorker = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    isLenient = true
    encodeDefaults = true
}

inline fun <reified T> String.toModel(): T? =
    try {
        jsonWorker.decodeFromString<T>(this)
    } catch (_: Exception) {
        null
    }


infix fun Double.format(
    decimals: Int
): String {
    val shifted = this * 10.0.pow(
        n = decimals
    )
    val rounded = round(
        x = shifted
    ).toLong()
    val str = rounded.toString()

    if (decimals <= 0) return str

    val integerPart = if (str.length <= decimals) "0" else str.substring(0, str.length - decimals)
    val fractionalPart = str.substring(
        startIndex = maxOf(
            a = 0,
            b = str.length - decimals
        )
    ).padStart(
        length = decimals,
        padChar = '0'
    )

    return "$integerPart.$fractionalPart"
}