package com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel

import com.agelousis.kotlinmultiplatform.network.NetworkHelper
import com.agelousis.kotlinmultiplatform.network.apis.EdamamAPI
import com.agelousis.kotlinmultiplatform.network.apis.createEdamamAPI
import com.agelousis.kotlinmultiplatform.network.repositories.GeneralRepository
import com.agelousis.kotlinmultiplatform.network.response.FoodParserResponseModel

//region Edamam APIs
suspend fun getFood(upc: String) {
    GeneralRepository.request<EdamamAPI, FoodParserResponseModel?>(
        api = NetworkHelper.ktorfit.createEdamamAPI(),
        requestInitializationBlock = EdamamAPI@ {
            this@EdamamAPI.foodParser(
                upc = upc
            )
        },
        successModelBlock = FoodParserResponseModel@ {

        },
        failureBlock = {

        }
    )
    //val response = edamamApi.foodParser(upc = upc)

}
//endregion