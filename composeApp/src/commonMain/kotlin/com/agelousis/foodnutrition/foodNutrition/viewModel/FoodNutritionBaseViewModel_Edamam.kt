package com.agelousis.foodnutrition.foodNutrition.viewModel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.viewModelScope
import com.agelousis.foodnutrition.foodNutrition.utils.FoodNutritionConstants
import com.agelousis.foodnutrition.network.NetworkHelper
import com.agelousis.foodnutrition.network.apis.EdamamAPI
import com.agelousis.foodnutrition.network.apis.createEdamamAPI
import com.agelousis.foodnutrition.network.models.IngredientModel
import com.agelousis.foodnutrition.network.repositories.GeneralRepository
import com.agelousis.foodnutrition.network.repositories.SuccessBlock
import com.agelousis.foodnutrition.network.repositories.SuspendedSuccessBlock
import com.agelousis.foodnutrition.network.request.IngredientsDataRequestModel
import com.agelousis.foodnutrition.network.response.FoodParserResponseModel
import com.agelousis.foodnutrition.network.response.IngredientsDataResponseModel
import com.agelousis.foodnutrition.utils.models.Quadruple
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
    quantity: Int = 100,
    successBlock: SuccessBlock<IngredientsDataResponseModel> = {}
) {
    viewModelScope.launch(
        context = Dispatchers.Default
    ) {
        isLoading = true
        parseFood(
            product = product,
            successBlock = FoodParserResponseModel@ {
                val (foodId, measureUri, modelFood, measures) =
                    Quadruple(
                        first = this@FoodParserResponseModel?.hints?.firstOrNull()?.food?.foodId
                            ?: return@FoodParserResponseModel,
                        second = this@FoodParserResponseModel.hints.firstOrNull()?.measures?.firstOrNull { measureModel ->
                            measureModel.label == FoodNutritionConstants.GRAM_VALUE
                        }?.uri,
                        third = this@FoodParserResponseModel.hints.firstOrNull()?.food,
                        fourth = this@FoodParserResponseModel.hints.firstOrNull()?.measures
                    )
                getFullyNutrition(
                    foodId = foodId,
                    measureUri = measureUri,
                    quantity = quantity,
                    successBlock = IngredientsDataResponseModel@ {
                        isLoading = false
                        val modelIngredients = this@IngredientsDataResponseModel?.copy(
                            modelFood = modelFood,
                            measures = measures
                        ) ?: return@IngredientsDataResponseModel
                        currentIngredientsDataResponseModelState = modelIngredients
                        this@requestFoodNutrition saveRecentSearch modelIngredients
                        foodDataStateMap[
                                product
                        ] = modelIngredients
                        successBlock(
                            modelIngredients
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
            isLoading = false
            alertPair = error.error to error.message
            showDialog()
        }
    )
}

private suspend fun FoodNutritionBaseViewModel.getFullyNutrition(
    foodId: String,
    measureUri: String?,
    quantity: Int,
    successBlock: SuspendedSuccessBlock<IngredientsDataResponseModel?>
) {
    GeneralRepository.request<EdamamAPI, IngredientsDataResponseModel?>(
        api = NetworkHelper.ktorfit.createEdamamAPI(),
        requestInitializationBlock = EdamamAPI@ {
            this@EdamamAPI.requestNutrients(
                ingredientsDataRequestModel = IngredientsDataRequestModel(
                    ingredients = listOf(
                        IngredientModel(
                            quantity = quantity,
                            foodId = foodId,
                            measureURI = measureUri
                        )
                    )
                )
            )
        },
        successModelBlock = successBlock,
        failureBlock = { error ->
            isLoading = false
            alertPair = error.error to error.message
            showDialog()
        }
    )
}
//endregion