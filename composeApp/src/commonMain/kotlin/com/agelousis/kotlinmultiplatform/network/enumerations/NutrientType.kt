package com.agelousis.kotlinmultiplatform.network.enumerations

enum class NutrientType(
    val value: String,
    val label: String
) {
    ENERGY_KCAL(value = "ENERC_KCAL", label = "Energy"),
    FAT(value = "FAT", label = "Total Fat"),
    SATURATED_FAT(value = "FASAT", label = "Saturated Fat"),
    MONOUNSATURATED_FAT(value = "FAMS", label = "Monounsaturated Fat"),
    POLYUNSATURATED_FAT(value = "FAPU", label = "Polyunsaturated Fat"),
    TRANS_FAT(value = "FATRN", label = "Trans Fat"),
    CHOLESTEROL(value = "CHOLE", label = "Cholesterol"),
    SODIUM(value = "NA", label = "Sodium"),
    TOTAL_CARBOHYDRATE(value = "CHOCDF", label = "Total Carbohydrate"),
    DIETARY_FIBER(value = "FIBTG", label = "Dietary Fiber"),
    TOTAL_SUGARS(value = "SUGAR", label = "Total Sugars"),
    ADDED_SUGARS(value = "SUGAR.added", label = "Added Sugars"),
    PROTEIN(value = "PROCNT", label = "Protein"),
    VITAMIN_D(value = "VITD", label = "Vitamin D"),
    CALCIUM(value = "CA", label = "Calcium"),
    IRON(value = "FE", label = "Iron"),
    POTASSIUM(value = "K", label = "Potassium"),
    VITAMIN_C(value = "VITC", label = "Vitamin C"),
    NET_CARBOHYDRATES(value = "CHOCDF.net", label = "Net Carbohydrates"),
    MAGNESIUM(value = "MG", label = "Magnesium"),
    PHOSPHORUS(value = "P", label = "Phosphorus"),
    THIAMIN(value = "THIA", label = "Thiamin (B1)");

    val hasPadding
        get() = when (this) {
            SATURATED_FAT,
            TRANS_FAT,
            DIETARY_FIBER,
            TOTAL_SUGARS,
            ADDED_SUGARS -> true
            else -> false
        }

    val isBold
        get() = when(this) {
            FAT,
            CHOLESTEROL,
            SODIUM,
            TOTAL_CARBOHYDRATE,
            PROTEIN -> true
            else -> false
        }

}