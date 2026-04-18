package com.agelousis.kotlinmultiplatform.network.enumerations

enum class NutrientType(val value: String) {
    ENERC_KCAL(value = "ENERC_KCAL"),
    FAT(value = "FAT"),
    FASAT(value = "FASAT"),
    FATRN(value = "FATRN"),
    CHOLE(value = "CHOLE"),
    NA(value = "NA"),
    CHOCDF(value = "CHOCDF"),
    FIBTG(value = "FIBTG"),
    SUGAR(value = "SUGAR"),
    SUGAR_added(value = "SUGAR.added"),
    PROCNT(value = "PROCNT"),
    VITD(value = "VITD"),
    CA(value = "CA"),
    FE(value = "FE"),
    K(value = "K"),
    VITC(value = "VITC"),
    FAMS(value = "FAMS"),
    FAPU(value = "FAPU"),
    CHOCDF_net(value = "CHOCDF.net"),
    MG(value = "MG"),
    P(value = "P"),
    THIA(value = "THIA");

    val hasPadding
        get() = when (this) {
            FASAT,
            FATRN,
            FIBTG,
            SUGAR,
            SUGAR_added -> true
            else -> false
        }

    val isBold
        get() = when(this) {
            FAT,
            CHOLE,
            NA,
            CHOCDF,
            PROCNT -> true
            else -> false
        }

}