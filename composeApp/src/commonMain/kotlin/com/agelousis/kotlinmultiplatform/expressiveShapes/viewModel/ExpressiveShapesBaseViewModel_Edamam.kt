package com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel

import com.agelousis.kotlinmultiplatform.network.repositories.GeneralRepository

//region Edamam APIs
suspend fun getFood(upc: String) {
    val response = GeneralRepository.edamamApi.foodParser(upc = upc)
}
//endregion