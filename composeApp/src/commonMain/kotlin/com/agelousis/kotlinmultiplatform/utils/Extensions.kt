package com.agelousis.kotlinmultiplatform.utils

import kotlinx.serialization.json.Json

typealias SuccessUnitBlock = () -> Unit
typealias SuccessBlock<T> = T.() -> Unit

val jsonWorker = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    isLenient = true
}

inline fun <reified T> String.toModel(): T? =
    try {
        jsonWorker.decodeFromString<T>(this)
    } catch (_: Exception) {
        null
    }