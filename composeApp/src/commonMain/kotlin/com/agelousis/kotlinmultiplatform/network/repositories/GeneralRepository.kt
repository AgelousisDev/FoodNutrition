package com.agelousis.kotlinmultiplatform.network.repositories

import com.agelousis.kotlinmultiplatform.network.response.ErrorModel

typealias SuccessUnitBlock = () -> Unit
typealias SuccessBlock<T> = T.() -> Unit
typealias FailureBlock = (ErrorModel) -> Unit

typealias RequestInitializationBlock <I, R> = suspend I.() -> R

object GeneralRepository {

    suspend inline fun <reified I, R> request(
        api: I,
        crossinline requestInitializationBlock: RequestInitializationBlock<I, R>,
        noinline successModelBlock: SuccessBlock<R>,
        noinline failureBlock: FailureBlock
    ) {
        try {
            val response = api.requestInitializationBlock()
            successModelBlock(response)
        } catch (e: Exception) {
            failureBlock(
                ErrorModel(
                    message = e.message
                        ?: "Network error"
                )
            )
        }
    }

}