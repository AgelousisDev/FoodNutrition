package com.agelousis.kotlinmultiplatform.network.response

import com.agelousis.kotlinmultiplatform.network.enumerations.NutrientType
import com.agelousis.kotlinmultiplatform.network.models.IngredientModel
import com.agelousis.kotlinmultiplatform.network.models.NutrientInfoModel
import com.agelousis.kotlinmultiplatform.utils.toModel
import kotlinx.serialization.Serializable

val INGREDIENTS_DATA_RESPONSE_MOCK_MODEL =
    """
    {
      "uri": "http://www.edamam.com/ontologies/edamam.owl#81e8f8a3-e4f1-4602-b47c-d968668ddf76",
      "calories": 160,
      "totalWeight": 100,
      "dietLabels": [],
      "healthLabels": [
        "VEGAN", "VEGETARIAN", "PESCATARIAN", "PALEO", "SPECIFIC_CARBS",
        "DAIRY_FREE", "GLUTEN_FREE", "WHEAT_FREE", "EGG_FREE", "MILK_FREE",
        "PEANUT_FREE", "TREE_NUT_FREE", "SOY_FREE", "FISH_FREE", "SHELLFISH_FREE",
        "PORK_FREE", "RED_MEAT_FREE", "CRUSTACEAN_FREE", "CELERY_FREE", 
        "MUSTARD_FREE", "SESAME_FREE", "LUPINE_FREE", "MOLLUSK_FREE", 
        "ALCOHOL_FREE", "NO_OIL_ADDED", "NO_SUGAR_ADDED", "KOSHER"
      ],
      "cautions": [
        "SULFITES"
      ],
      "totalNutrients": {
        "ENERC_KCAL": { "label": "Energy", "quantity": 160, "unit": "kcal" },
        "FAT": { "label": "Fat", "quantity": 14.7, "unit": "g" },
        "FASAT": { "label": "Saturated", "quantity": 2.13, "unit": "g" },
        "FATRN": { "label": "Trans", "quantity": 0, "unit": "g" },
        "FAMS": { "label": "Monounsaturated", "quantity": 9.8, "unit": "g" },
        "FAPU": { "label": "Polyunsaturated", "quantity": 1.82, "unit": "g" },
        "CHOCDF": { "label": "Carbs", "quantity": 8.53, "unit": "g" },
        "CHOCDF.net": { "label": "Carbohydrates (net)", "quantity": 1.8299999999999992, "unit": "g" },
        "FIBTG": { "label": "Fiber", "quantity": 6.7, "unit": "g" },
        "SUGAR": { "label": "Sugars", "quantity": 0.66, "unit": "g" },
        "SUGAR.added": { "label": "Sugars, added", "quantity": 0, "unit": "g" },
        "PROCNT": { "label": "Protein", "quantity": 2, "unit": "g" },
        "CHOLE": { "label": "Cholesterol", "quantity": 0, "unit": "mg" },
        "NA": { "label": "Sodium", "quantity": 7.000000000000001, "unit": "mg" },
        "CA": { "label": "Calcium", "quantity": 12, "unit": "mg" },
        "MG": { "label": "Magnesium", "quantity": 28.999999999999996, "unit": "mg" },
        "K": { "label": "Potassium", "quantity": 485.00000000000006, "unit": "mg" },
        "FE": { "label": "Iron", "quantity": 0.55, "unit": "mg" },
        "ZN": { "label": "Zinc", "quantity": 0.64, "unit": "mg" },
        "P": { "label": "Phosphorus", "quantity": 52, "unit": "mg" },
        "VITA_RAE": { "label": "Vitamin A", "quantity": 7.000000000000001, "unit": "µg" },
        "VITC": { "label": "Vitamin C", "quantity": 10, "unit": "mg" },
        "THIA": { "label": "Thiamin (B1)", "quantity": 0.067, "unit": "mg" },
        "RIBF": { "label": "Riboflavin (B2)", "quantity": 0.13, "unit": "mg" },
        "NIA": { "label": "Niacin (B3)", "quantity": 1.7399999999999998, "unit": "mg" },
        "VITB6A": { "label": "Vitamin B6", "quantity": 0.257, "unit": "mg" },
        "FOLDFE": { "label": "Folate equivalent (total)", "quantity": 81, "unit": "µg" },
        "FOLFD": { "label": "Folate (food)", "quantity": 81, "unit": "µg" },
        "FOLAC": { "label": "Folic acid", "quantity": 0, "unit": "µg" },
        "VITB12": { "label": "Vitamin B12", "quantity": 0, "unit": "µg" },
        "VITD": { "label": "Vitamin D", "quantity": 0, "unit": "µg" },
        "TOCPHA": { "label": "Vitamin E", "quantity": 2.07, "unit": "mg" },
        "VITK1": { "label": "Vitamin K", "quantity": 21, "unit": "µg" },
        "Sugar.alcohol": { "label": "Sugar alcohol", "quantity": 0, "unit": "g" },
        "WATER": { "label": "Water", "quantity": 73.2, "unit": "g" }
      },
      "totalDaily": {
        "ENERC_KCAL": { "label": "Energy", "quantity": 6.828123332977702, "unit": "%" },
        "FAT": { "label": "Fat", "quantity": 18.82001493651979, "unit": "%" },
        "FASAT": { "label": "Saturated", "quantity": 9.089939187026566, "unit": "%" },
        "CHOCDF": { "label": "Carbs", "quantity": 2.9121946015149893, "unit": "%" },
        "CHOCDF.net": { "label": "Carbohydrates (net)", "quantity": 0, "unit": "g" },
        "FIBTG": { "label": "Fiber", "quantity": 17.63157894736842, "unit": "%" },
        "PROCNT": { "label": "Protein", "quantity": 1.7070308332444255, "unit": "%" },
        "CHOLE": { "label": "Cholesterol", "quantity": 0, "unit": "%" },
        "NA": { "label": "Sodium", "quantity": 0.46666666666666673, "unit": "%" },
        "CA": { "label": "Calcium", "quantity": 0.9230769230769231, "unit": "%" },
        "MG": { "label": "Magnesium", "quantity": 6.904761904761903, "unit": "%" },
        "K": { "label": "Potassium", "quantity": 14.264705882352944, "unit": "%" },
        "FE": { "label": "Iron", "quantity": 3.055555555555556, "unit": "%" },
        "ZN": { "label": "Zinc", "quantity": 5.818181818181818, "unit": "%" },
        "P": { "label": "Phosphorus", "quantity": 4.16, "unit": "%" },
        "VITA_RAE": { "label": "Vitamin A", "quantity": 0.7777777777777779, "unit": "%" },
        "VITC": { "label": "Vitamin C", "quantity": 11.11111111111111, "unit": "%" },
        "THIA": { "label": "Thiamin (B1)", "quantity": 5.583333333333334, "unit": "%" },
        "RIBF": { "label": "Riboflavin (B2)", "quantity": 10, "unit": "%" },
        "NIA": { "label": "Niacin (B3)", "quantity": 10.874999999999998, "unit": "%" },
        "VITB6A": { "label": "Vitamin B6", "quantity": 15.117647058823529, "unit": "%" },
        "FOLDFE": { "label": "Folate equivalent (total)", "quantity": 20.25, "unit": "%" },
        "VITB12": { "label": "Vitamin B12", "quantity": 0, "unit": "%" },
        "VITD": { "label": "Vitamin D", "quantity": 0, "unit": "%" },
        "TOCPHA": { "label": "Vitamin E", "quantity": 13.799999999999999, "unit": "%" },
        "VITK1": { "label": "Vitamin K", "quantity": 17.5, "unit": "%" }
      },
      "ingredients": [
        {
          "parsed": [
            {
              "quantity": 100,
              "measure": "gram",
              "food": "avocado",
              "foodId": "food_b0yuze4b1g3afpanijno5abtiu28",
              "weight": 100,
              "retainedWeight": 100,
              "measureURI": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
              "status": "OK"
            }
          ]
        }
      ]
    }
    """.trimIndent().toModel<IngredientsDataResponseModel?>()

@Serializable
data class IngredientsDataResponseModel(
    val uri: String?,
    val calories: Double?,
    val totalWeight: Double?,
    val dietLabels: List<String>?,
    val healthLabels: List<String>?,
    val cautions: List<String?>,
    val totalNutrients: Map<String, NutrientInfoModel>?,
    val totalDaily: Map<String, NutrientInfoModel>?,
    val ingredients: List<IngredientModel>?
) {

    val nutrientInfoModelList: List<Triple<NutrientType, NutrientInfoModel?, NutrientInfoModel?>>
        get() = NutrientType.entries.map { nutrientType ->
            val info = totalNutrients?.get(nutrientType.value)
            val dailyInfo = totalDaily?.get(nutrientType.value)
            Triple(
                first = nutrientType,
                second = info,
                third = dailyInfo
            )
        }

}