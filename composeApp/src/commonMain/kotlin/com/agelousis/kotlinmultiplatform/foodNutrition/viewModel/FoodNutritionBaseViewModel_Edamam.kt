package com.agelousis.kotlinmultiplatform.foodNutrition.viewModel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.viewModelScope
import com.agelousis.kotlinmultiplatform.network.NetworkHelper
import com.agelousis.kotlinmultiplatform.network.apis.EdamamAPI
import com.agelousis.kotlinmultiplatform.network.apis.createEdamamAPI
import com.agelousis.kotlinmultiplatform.network.models.IngredientModel
import com.agelousis.kotlinmultiplatform.network.repositories.GeneralRepository
import com.agelousis.kotlinmultiplatform.network.repositories.SuccessBlock
import com.agelousis.kotlinmultiplatform.network.repositories.SuspendedSuccessBlock
import com.agelousis.kotlinmultiplatform.network.request.IngredientsDataRequestModel
import com.agelousis.kotlinmultiplatform.network.response.FoodParserResponseModel
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.network.response.enumerations.ServingSizeMetricType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.getValue

//region Edamam APIs

val FoodNutritionBaseViewModel.foodDataStateMap by lazy {
    mutableStateMapOf<String, IngredientsDataResponseModel>()
}

infix fun FoodNutritionBaseViewModel.foodData(
    product: String
) = foodDataStateMap[product]

fun FoodNutritionBaseViewModel.requestFoodNutrition(
    product: String,
    successBlock: SuccessBlock<IngredientsDataResponseModel> = {}
) {
    viewModelScope.launch(
        context = Dispatchers.Default
    ) {
        parseFood(
            product = product,
            successBlock = FoodParserResponseModel@ {
                val (foodId, measureUri) = (this@FoodParserResponseModel?.hints?.firstOrNull()?.food?.foodId
                    ?: return@FoodParserResponseModel) to (this@FoodParserResponseModel.hints.firstOrNull()?.measures?.firstOrNull { measureModel ->
                    measureModel.label == ServingSizeMetricType.GRAM.value
                }?.uri)
                getFullyNutrition(
                    foodId = foodId,
                    measureUri = measureUri,
                    successBlock = IngredientsDataResponseModel@ {
                        foodDataStateMap[
                                product
                        ] = this@IngredientsDataResponseModel
                            ?: return@IngredientsDataResponseModel
                        successBlock(
                            this@IngredientsDataResponseModel
                        )
                    }
                )
            }
        )
    }

}

private suspend fun FoodNutritionBaseViewModel.parseFood(
    product: String,
    successBlock: SuspendedSuccessBlock<FoodParserResponseModel?>
) {
    GeneralRepository.request<EdamamAPI, FoodParserResponseModel?>(
        api = NetworkHelper.ktorfit.createEdamamAPI(),
        requestInitializationBlock = EdamamAPI@ {
            this@EdamamAPI.foodParser(
                product = product
            )
        },
        successModelBlock = successBlock,
        failureBlock = { error ->
            alertPair = error.error to error.message
            showDialog()
        }
    )
}

private suspend fun FoodNutritionBaseViewModel.getFullyNutrition(
    foodId: String,
    measureUri: String?,
    successBlock: SuspendedSuccessBlock<IngredientsDataResponseModel?>
) {
    GeneralRepository.request<EdamamAPI, IngredientsDataResponseModel?>(
        api = NetworkHelper.ktorfit.createEdamamAPI(),
        requestInitializationBlock = EdamamAPI@ {
            this@EdamamAPI.requestNutrients(
                ingredientsDataRequestModel = IngredientsDataRequestModel(
                    ingredients = listOf(
                        IngredientModel(
                            quantity = 100,
                            foodId = foodId,
                            measureURI = measureUri
                        )
                    )
                )
            )
        },
        successModelBlock = successBlock,
        failureBlock = { error ->
            alertPair = error.error to error.message
            showDialog()
        }
    )
}
//endregion