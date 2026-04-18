package com.agelousis.kotlinmultiplatform.network.response

data class FoodModel(
    val brand: String?,
    val category: String?,
    val categoryLabel: String?,
    val foodContentsLabel: String?,
    val image: String?,
    val foodId: String?,
    val knownAs: String?,
    var label: String?,
    val nutrients: NutrientModel?,
    val servingSizes: List<ServingSizeModel>?,
    val quantity: Double?,
    val measure: String?,
     val food: String?,
    val weight: Double?,
    val retainedWeight: Double?,
    val servingsPerContainer: Double?,
    val measureUri: String?,
    val status: String?
) {

    val foodInfoList
        get() = arrayOf(
            "$categoryLabel - $category",
            brand,
            foodContentsLabel?.replace(";", ",")
        ).filterNotNull()

    /*infix fun nutritionInfoPairList(
        context: Context
    ) = context.resources.getStringArray(R.array.key_nutrition_info_array).mapIndexed { index, s ->
        s.format(
            nutrients?.nutritionValues?.getOrNull(index = index) ?: 0.0) to
                "%d%%".format(((100 * (nutrients?.nutritionValues?.getOrNull(index = index) ?: 0.0)) / (servingSizes?.firstOrNull { servingSizeModel ->
                    servingSizeModel.label == ServingSizeMetricType.GRAM
                }?.quantity ?: 0.0)).toInt())
    }*/

}