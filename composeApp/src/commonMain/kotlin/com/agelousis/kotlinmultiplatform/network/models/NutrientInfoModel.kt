package com.agelousis.kotlinmultiplatform.network.models

data class NutrientInfoModel(
    val label: String?,
    val quantity: Double?,
    val unit: String?
) {

    /*infix fun linkTextDataModelList(
        nutrientType: NutrientType
    ) =
        if (
            label != null
            && quantity != null
            && unit != null
        )
            listOf(
                LinkTextData(
                    text = label,
                    fontWeight = if (nutrientType.isBold) FontWeight.Bold else FontWeight.Light
                ),
                LinkTextData(
                    text = " %.2f".format(quantity),
                ),
                LinkTextData(
                    text = unit,
                )
            )
        else
            listOf()*/

}