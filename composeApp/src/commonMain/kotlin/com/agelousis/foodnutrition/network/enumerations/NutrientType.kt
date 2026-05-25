package com.agelousis.foodnutrition.network.enumerations

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
    TOTAL_CARBOHYDRATE(value = "CHOCDF", label = "Total Carbohydrate"),
    DIETARY_FIBER(value = "FIBTG", label = "Dietary Fiber"),
    TOTAL_SUGARS(value = "SUGAR", label = "Total Sugars"),
    ADDED_SUGARS(value = "SUGAR.added", label = "Added Sugars"),
    NET_CARBOHYDRATES(value = "CHOCDF.net", label = "Net Carbohydrates"),
    PROTEIN(value = "PROCNT", label = "Protein"),
    SODIUM(value = "NA", label = "Sodium"),
    CHOLESTEROL(value = "CHOLE", label = "Cholesterol"),
    VITAMIN_D(value = "VITD", label = "Vitamin D"),
    CALCIUM(value = "CA", label = "Calcium"),
    IRON(value = "FE", label = "Iron"),
    POTASSIUM(value = "K", label = "Potassium"),
    VITAMIN_C(value = "VITC", label = "Vitamin C"),
    MAGNESIUM(value = "MG", label = "Magnesium"),
    PHOSPHORUS(value = "P", label = "Phosphorus"),
    THIAMIN(value = "THIA", label = "Thiamin (B1)");

    val hasPadding
        get() = when (this) {
            SATURATED_FAT,
            MONOUNSATURATED_FAT,
            POLYUNSATURATED_FAT,
            TRANS_FAT,
            DIETARY_FIBER,
            TOTAL_SUGARS,
            ADDED_SUGARS,
            NET_CARBOHYDRATES -> true
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