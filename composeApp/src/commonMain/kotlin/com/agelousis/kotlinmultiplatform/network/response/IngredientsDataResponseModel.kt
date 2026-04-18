package com.agelousis.kotlinmultiplatform.network.response

import com.agelousis.kotlinmultiplatform.network.models.IngredientModel
import com.agelousis.kotlinmultiplatform.utils.toModel

val INGREDIENTS_DATA_RESPONSE_MOCK_MODEL =
    "{\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#6df73cb8-2490-4853-abb8-0f49dcc17fb7\",\"calories\":412,\"totalWeight\":100.0,\"dietLabels\":[],\"healthLabels\":[\"VEGETARIAN\",\"PESCATARIAN\",\"EGG_FREE\",\"PEANUT_FREE\",\"SOY_FREE\",\"FISH_FREE\",\"SHELLFISH_FREE\",\"PORK_FREE\",\"RED_MEAT_FREE\",\"CRUSTACEAN_FREE\",\"CELERY_FREE\",\"MUSTARD_FREE\",\"SESAME_FREE\",\"LUPINE_FREE\",\"MOLLUSK_FREE\",\"ALCOHOL_FREE\",\"KOSHER\"],\"cautions\":[\"TREE_NUTS\",\"SULFITES\"],\"totalNutrients\":{\"ENERC_KCAL\":{\"label\":\"Energy\",\"quantity\":412.0,\"unit\":\"kcal\"},\"FAT\":{\"label\":\"Total lipid (fat)\",\"quantity\":10.380000114440918,\"unit\":\"g\"},\"FASAT\":{\"label\":\"Fatty acids, total saturated\",\"quantity\":1.1200000047683716,\"unit\":\"g\"},\"FATRN\":{\"label\":\"Fatty acids, total trans\",\"quantity\":0.10999999940395355,\"unit\":\"g\"},\"FAMS\":{\"label\":\"Fatty acids, total monounsaturated\",\"quantity\":5.869999885559082,\"unit\":\"g\"},\"FAPU\":{\"label\":\"Fatty acids, total polyunsaturated\",\"quantity\":2.380000114440918,\"unit\":\"g\"},\"CHOCDF\":{\"label\":\"Carbohydrate, by difference\",\"quantity\":74.66999816894531,\"unit\":\"g\"},\"CHOCDF.net\":{\"label\":\"Carbohydrates (net)\",\"quantity\":65.26999855041504,\"unit\":\"g\"},\"FIBTG\":{\"label\":\"Fiber, total dietary\",\"quantity\":9.399999618530273,\"unit\":\"g\"},\"SUGAR\":{\"label\":\"Sugars, total including NLEA\",\"quantity\":24.6299991607666,\"unit\":\"g\"},\"PROCNT\":{\"label\":\"Protein\",\"quantity\":9.65999984741211,\"unit\":\"g\"},\"CHOLE\":{\"label\":\"Cholesterol\",\"quantity\":2.0,\"unit\":\"mg\"},\"NA\":{\"label\":\"Sodium, Na\",\"quantity\":54.0,\"unit\":\"mg\"},\"CA\":{\"label\":\"Calcium, Ca\",\"quantity\":101.0,\"unit\":\"mg\"},\"MG\":{\"label\":\"Magnesium, Mg\",\"quantity\":110.0,\"unit\":\"mg\"},\"K\":{\"label\":\"Potassium, K\",\"quantity\":501.0,\"unit\":\"mg\"},\"FE\":{\"label\":\"Iron, Fe\",\"quantity\":2.690000057220459,\"unit\":\"mg\"},\"P\":{\"label\":\"Phosphorus, P\",\"quantity\":357.0,\"unit\":\"mg\"},\"VITC\":{\"label\":\"Vitamin C, total ascorbic acid\",\"quantity\":0.5,\"unit\":\"mg\"},\"THIA\":{\"label\":\"Thiamin\",\"quantity\":0.0,\"unit\":\"mg\"}},\"totalDaily\":{\"ENERC_KCAL\":{\"label\":\"Energy\",\"quantity\":20.6,\"unit\":\"%\"},\"FAT\":{\"label\":\"Fat\",\"quantity\":15.96923094529372,\"unit\":\"%\"},\"FASAT\":{\"label\":\"Saturated\",\"quantity\":5.600000023841858,\"unit\":\"%\"},\"CHOCDF\":{\"label\":\"Carbs\",\"quantity\":24.889999389648438,\"unit\":\"%\"},\"FIBTG\":{\"label\":\"Fiber\",\"quantity\":37.599998474121094,\"unit\":\"%\"},\"PROCNT\":{\"label\":\"Protein\",\"quantity\":19.31999969482422,\"unit\":\"%\"},\"CHOLE\":{\"label\":\"Cholesterol\",\"quantity\":0.6666666666666666,\"unit\":\"%\"},\"NA\":{\"label\":\"Sodium\",\"quantity\":2.25,\"unit\":\"%\"},\"CA\":{\"label\":\"Calcium\",\"quantity\":10.1,\"unit\":\"%\"},\"MG\":{\"label\":\"Magnesium\",\"quantity\":26.19047619047619,\"unit\":\"%\"},\"K\":{\"label\":\"Potassium\",\"quantity\":10.659574468085106,\"unit\":\"%\"},\"FE\":{\"label\":\"Iron\",\"quantity\":14.944444762335884,\"unit\":\"%\"},\"P\":{\"label\":\"Phosphorus\",\"quantity\":51.0,\"unit\":\"%\"},\"VITC\":{\"label\":\"Vitamin C\",\"quantity\":0.5555555555555556,\"unit\":\"%\"},\"THIA\":{\"label\":\"Thiamin (B1)\",\"quantity\":0.0,\"unit\":\"%\"}},\"ingredients\":[{\"parsed\":[{\"quantity\":100.0,\"measure\":\"gram\",\"food\":\"Quaker Simply Granola Oats/Honey/Raisins/Almond 28 Ounce Paper Box\",\"foodId\":\"food_a2oz7e4bt7myy9bnthlmpbfkoomj\",\"brand\":\"Quaker\",\"foodContentsLabel\":\"WHOLE GRAIN ROLLED OATS; WHOLE GRAIN ROLLED WHEAT; BROWN SUGAR; RAISINS; CANOLA OIL; WHEY; INULIN; ALMONDS; NONFAT DRY MILK; GLYCERIN; WHEY PROTEIN CONCENTRATE; HONEY; NATURAL FLAVOR (CONTAINS COCONUT COMPONENTS); SUNFLOWER OIL; TOCOPHEROLS (ADDED TO PRESERVE FRESHNESS).\",\"weight\":100.0,\"retainedWeight\":100.0,\"servingSizes\":[{\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\",\"label\":\"Gram\",\"quantity\":51.0},{\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_cup\",\"label\":\"Cup\",\"quantity\":0.5}],\"servingsPerContainer\":16.0,\"measureURI\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\",\"status\":\"OK\"}]}]}"
        .toModel<IngredientsDataResponseModel>()

data class IngredientsDataResponseModel(
    val uri: String?,
    val calories: Double?,
    val totalWeight: Double?,
    val dietLabels: List<String>?,
    val healthLabels: List<String>?,
    val cautions: List<String?>,
    val totalNutrients: Any?,
    val totalDaily: Any?,
    val ingredients: List<IngredientModel>?
) {

    /*val nutrientInfoModelList: List<Triple<NutrientType, NutrientInfoModel, NutrientInfoModel>>
        get() {
            val nutrientInfoModelPairList =
                mutableListOf<Triple<NutrientType, NutrientInfoModel, NutrientInfoModel>>()
            val totalNutrientsJsonObject = JSONObject(
                totalNutrients as? Map<*, *>
                    ?: return listOf()
            )
            val totalDailyNutrientsJsonObject = JSONObject(
                totalDaily as? Map<*, *>
                    ?: return listOf()
            )
            NutrientType.entries.forEach { nutrientType ->
                if (
                    totalNutrientsJsonObject.has(nutrientType.value)
                    && totalDailyNutrientsJsonObject.has(nutrientType.value)
                )
                    nutrientInfoModelPairList.add(
                        Triple(
                            first = nutrientType,
                            second = totalNutrientsJsonObject.getJSONObject(
                                nutrientType.value
                            ).toString().toModel<NutrientInfoModel>()
                                ?: return@forEach,
                            third = totalDailyNutrientsJsonObject.getJSONObject(
                                nutrientType.value
                            ).toString().toModel<NutrientInfoModel>()
                                ?: return@forEach
                        )
                    )
            }
            return nutrientInfoModelPairList
        }*/

}