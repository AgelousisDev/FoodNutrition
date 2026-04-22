package com.agelousis.kotlinmultiplatform.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutrientModel(
    @SerialName(value = "ENERC_KCAL") val calories: Double?,
    @SerialName(value = "FAT") val fat: Double?,
    @SerialName(value = "FASAT") val saturatedFat: Double?,
    @SerialName(value = "FATRN") val transFat: Double?,
    @SerialName(value = "CHOLE") val cholesterol: Double?,
    @SerialName(value = "NA") val sodium: Double?,
    @SerialName(value = "CHOCDF") val totalCarbohydrate: Double?,
    @SerialName(value = "FIBTG") val dietaryFiber: Double?,
    @SerialName(value = "SUGAR") val totalSugars: Double?,
    @SerialName(value = "SUGAR.added") val addedSugar: Double?,
    @SerialName(value = "PROCNT") val protein: Double?,
    @SerialName(value = "VITD") val vitaminD: Double?,
    @SerialName(value = "CA") val calcium: Double?,
    @SerialName(value = "FE") val iron: Double?,
    @SerialName(value = "K") val potassium: Double?,
    @SerialName(value = "VITC") val vitaminC: Double?,
    @SerialName(value = "FAMS") val totalFattyAcidsMonounsaturated: Double?,
    @SerialName(value = "FAPU") val totalFattyAcidsPolyunsaturated: Double?,
    @SerialName(value = "MG") val magnesium: Double?,
    @SerialName(value = "P") val phosphorus: Double?,
    @SerialName(value = "THIA") val thiamin: Double?
) {

    val nutritionValues
        get() = listOf(
            fat ?: 0.0,
            saturatedFat ?: 0.0,
            transFat ?: 0.0,
            cholesterol ?: 0.0,
            sodium ?: 0.0,
            totalCarbohydrate ?: 0.0,
            dietaryFiber ?: 0.0,
            totalSugars ?: 0.0,
            addedSugar ?: 0.0,
            protein ?: 0.0,
            vitaminD ?: 0.0,
            calcium ?: 0.0,
            iron ?: 0.0,
            potassium ?: 0.0,
            vitaminC ?: 0.0
        )

}
// (100 * value) / 56 = ?
/*
SUGAR.added	Added sugar	g
CA	Calcium, Ca	mg
CHOCDF.net	Carbohydrate (net)	g
CHOCDF	Carbohydrate, by difference	g
CHOLE	Cholesterol	mg
ENERC_KCAL	Energy	kcal
FAMS	Fatty acids, total monounsaturated	g
FAPU	Fatty acids, total polyunsaturated	g
FASAT	Fatty acids, total saturated	g
FATRN	Fatty acids, total trans	g
FIBTG	Fiber, total dietary	g
FOLDFE	Folate, DFE	µg
FOLFD	Folate, food	µg
FOLAC	Folic acid	µg
FE	Iron, Fe	mg
MG	Magnesium	mg
NIA	Niacin	mg
P	Phosphorus, P	mg
K	Potassium, K	mg
PROCNT	Protein	g
RIBF	Riboflavin	mg
NA	Sodium, Na	mg
Sugar.alcohol	Sugar alcohols	g
SUGAR	Sugars, total	g
THIA	Thiamin	mg
FAT	Total lipid (fat)	g
VITA_RAE	Vitamin A, RAE	µg
VITB12	Vitamin B-12	µg
VITB6A	Vitamin B-6	mg
VITC	Vitamin C, total ascorbic acid	mg
VITD	Vitamin D (D2 + D3)	µg
TOCPHA	Vitamin E (alpha-tocopherol)	mg
VITK1	Vitamin K (phylloquinone)	µg
WATER	Water	g
ZN	Zinc, Zn	mg

 */