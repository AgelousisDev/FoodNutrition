package com.agelousis.kotlinmultiplatform.network.repositories

import com.agelousis.kotlinmultiplatform.network.response.ErrorModel

typealias SuccessUnitBlock = () -> Unit
typealias SuccessBlock<T> = (T) -> Unit
typealias FailureBlock = (ErrorModel) -> Unit

typealias RequestInitializationBlock <I, R> = I.() -> R

object GeneralRepository {

    suspend inline fun <reified I, R> request(
        crossinline requestInitializationBlock: RequestInitializationBlock<I, R>,
        noinline successModelBlock: SuccessBlock<R>,
        noinline failureBlock: FailureBlock
    ) {
        try {
            // Get the API instance (This logic assumes I is the API interface)
            // For now, since we have a direct reference to edamamApi:
            val response = (edamamApi as I).requestInitializationBlock()
            successModelBlock(response)
        } catch (e: Exception) {
            failureBlock(ErrorModel(message = e.message ?: "Network error"))
        }
    }

}