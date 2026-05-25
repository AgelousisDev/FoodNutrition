package com.agelousis.foodnutrition.network.apis

import com.agelousis.foodnutrition.network.request.IngredientsDataRequestModel
import com.agelousis.foodnutrition.network.response.FoodParserResponseModel
import com.agelousis.foodnutrition.network.response.IngredientsDataResponseModel
import com.agelousis.foodnutrition.network.utils.ApiConstants
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query

interface EdamamAPI {
    @GET(value = "api/food-database/v2/parser")
    suspend fun foodParser(
        @Query(value = "app_id") appId: String = ApiConstants.EDAMAM_APPLICATION_ID,
        @Query(value = "app_key") appKey: String = ApiConstants.EDAMAM_APPLICATION_KEY,
        @Query(value = "ingr") product: String,
        @Query(value = "nutrition-type") nutritionType: String = "logging"
    ): FoodParserResponseModel

    @POST(value = "api/food-database/v2/nutrients")
    suspend fun requestNutrients(
        @Query(value = "app_id") appId: String = ApiConstants.EDAMAM_APPLICATION_ID,
        @Query(value = "app_key") appKey: String = ApiConstants.EDAMAM_APPLICATION_KEY,
        @Body ingredientsDataRequestModel: IngredientsDataRequestModel
    ): IngredientsDataResponseModel
}