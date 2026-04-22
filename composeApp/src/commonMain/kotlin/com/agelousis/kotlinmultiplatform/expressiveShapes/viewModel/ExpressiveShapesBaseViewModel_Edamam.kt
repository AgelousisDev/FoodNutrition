package com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel

import androidx.lifecycle.viewModelScope
import com.agelousis.kotlinmultiplatform.network.NetworkHelper
import com.agelousis.kotlinmultiplatform.network.apis.EdamamAPI
import com.agelousis.kotlinmultiplatform.network.apis.createEdamamAPI
import com.agelousis.kotlinmultiplatform.network.models.IngredientModel
import com.agelousis.kotlinmultiplatform.network.repositories.GeneralRepository
import com.agelousis.kotlinmultiplatform.network.repositories.SuspendedSuccessBlock
import com.agelousis.kotlinmultiplatform.network.request.IngredientsDataRequestModel
import com.agelousis.kotlinmultiplatform.network.response.FoodParserResponseModel
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import kotlinx.coroutines.launch
import kotlin.getValue

//region Edamam APIs

private val ExpressiveShapesBaseViewModel.foodDataStateMap by lazy {
    mutableMapOf<String, IngredientsDataResponseModel>()
}

infix fun ExpressiveShapesBaseViewModel.foodData(
    foodId: String
) = foodDataStateMap[foodId]

fun ExpressiveShapesBaseViewModel.getFoodNutrition(
    product: String
) {
    viewModelScope.launch {
        parseFood(
            product = product,
            successBlock = FoodParserResponseModel@ {
                val foodId = this@FoodParserResponseModel?.hints?.firstOrNull()?.foodModel?.foodId
                    ?: return@FoodParserResponseModel
                getFullyNutrition(
                    foodId = foodId,
                    successBlock = IngredientsDataResponseModel@ {
                        foodDataStateMap[
                                foodId
                        ] = this@IngredientsDataResponseModel
                            ?: return@IngredientsDataResponseModel
                    }
                )
            }
        )
    }

}

private suspend fun ExpressiveShapesBaseViewModel.parseFood(
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

private suspend fun ExpressiveShapesBaseViewModel.getFullyNutrition(
    foodId: String,
    successBlock: SuspendedSuccessBlock<IngredientsDataResponseModel?>
) {
    GeneralRepository.request<EdamamAPI, IngredientsDataResponseModel?>(
        api = NetworkHelper.ktorfit.createEdamamAPI(),
        requestInitializationBlock = EdamamAPI@ {
            this@EdamamAPI.requestNutrients(
                ingredientsDataRequestModel = IngredientsDataRequestModel(
                    ingredients = listOf(
                        IngredientModel(
                            quantity = 100.0,
                            foodId = foodId
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