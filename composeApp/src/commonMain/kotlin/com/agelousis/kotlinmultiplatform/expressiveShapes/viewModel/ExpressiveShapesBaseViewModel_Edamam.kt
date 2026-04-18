package com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel

import com.agelousis.kotlinmultiplatform.network.NetworkHelper
import com.agelousis.kotlinmultiplatform.network.repositories.GeneralRepository
import com.agelousis.kotlinmultiplatform.network.apis.createEdamamAPI

//region Edamam APIs
suspend fun getFood(upc: String) {
    val edamamApi = NetworkHelper.ktorfit.createEdamamAPI()
    val response = GeneralRepository.edamamApi.foodParser(upc = upc)
}
//endregion