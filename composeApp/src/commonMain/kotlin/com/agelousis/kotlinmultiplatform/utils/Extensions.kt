package com.agelousis.kotlinmultiplatform.utils

import androidx.compose.ui.unit.round
import kotlinx.serialization.json.Json
import kotlin.math.pow
import kotlin.math.round

typealias SuccessUnitBlock = () -> Unit
typealias SuccessBlock<T> = T.() -> Unit

val jsonWorker = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    isLenient = true
    encodeDefaults = true // Us
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
    val fractionalPart = str.substring(maxOf(0, str.length - decimals)).padStart(decimals, '0')

    return "$integerPart.$fractionalPart"
}

infix fun Context.imageRequest(
    data: Any?
) = data?.let {
    ImageRequest
        .Builder(
            context = this
        )
        .data(
            data = it
        )
        .build()
}