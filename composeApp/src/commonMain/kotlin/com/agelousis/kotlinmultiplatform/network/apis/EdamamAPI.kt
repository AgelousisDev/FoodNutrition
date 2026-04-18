package com.agelousis.kotlinmultiplatform.network.apis

import com.agelousis.kotlinmultiplatform.network.request.IngredientsDataRequestModel
import com.agelousis.kotlinmultiplatform.network.response.FoodParserResponseModel
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.network.utils.ApiConstants
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query

interface EdamamAPI {

    @GET(value = "/api/food-database/v2/parser")
    fun foodParser(
        @Query(value = "app_id") appId: String = ApiConstants.EDAMAM_APPLICATION_ID,
        @Query(value = "app_key") appKey: String = ApiConstants.EDAMAM_APPLICATION_KEY,
        @Query(value = "upc") upc: String? = null
    ): FoodParserResponseModel?

    @POST(value = "/api/food-database/v2/nutrients")
    fun requestNutrients(
        @Query(value = "app_id") appId: String = ApiConstants.EDAMAM_APPLICATION_ID,
        @Query(value = "app_key") appKey: String = ApiConstants.EDAMAM_APPLICATION_KEY,
        @Body ingredientsDataRequestModel: IngredientsDataRequestModel
    ): IngredientsDataResponseModel?


}