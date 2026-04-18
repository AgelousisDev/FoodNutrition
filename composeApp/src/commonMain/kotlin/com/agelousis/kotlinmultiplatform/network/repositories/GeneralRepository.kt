package com.agelousis.kotlinmultiplatform.network.repositories

import com.agelousis.kotlinmultiplatform.network.NetworkHelper
import com.agelousis.kotlinmultiplatform.network.response.ErrorModel
import com.agelousis.kotlinmultiplatform.network.createEdamamAPI

typealias SuccessUnitBlock = () -> Unit
typealias SuccessBlock<T> = (T) -> Unit
typealias FailureBlock = (ErrorModel) -> Unit

typealias RequestInitializationBlock <I, R> = I.() -> R

object GeneralRepository {

    val edamamApi = NetworkHelper.ktorfit.createEdamamAPI()

}