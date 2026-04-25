package com.agelousis.kotlinmultiplatform.foodNutrition.enumerations

import androidx.compose.runtime.Composable
import com.agelousis.kotlinmultiplatform.compose.theme.AvocadoIcon
import com.agelousis.kotlinmultiplatform.compose.theme.ChiaSeedIcon
import com.agelousis.kotlinmultiplatform.compose.theme.CocoaButterIcon
import com.agelousis.kotlinmultiplatform.compose.theme.EggsIcon
import com.agelousis.kotlinmultiplatform.compose.theme.FlaxSeedIcon
import com.agelousis.kotlinmultiplatform.compose.theme.GheeIcon
import com.agelousis.kotlinmultiplatform.compose.theme.OliveOilIcon
import com.agelousis.kotlinmultiplatform.compose.util.ScalingHorizontalPagerData
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_ketogenic_super_food_array
import kotlinmultiplatform.composeapp.generated.resources.key_ketogenic_super_food_descriptions_array
import org.jetbrains.compose.resources.stringArrayResource

enum class KetogenicSuperFood: ScalingHorizontalPagerData {
    AVOCADO,
    FLAX_SEED,
    CHIA_SEED,
    OLIVE_OIL,
    EGGS,
    GHEE,
    COCOA_BUTTER;

    override val icon: Any
        get() = when (this) {
            AVOCADO -> AvocadoIcon
            FLAX_SEED -> FlaxSeedIcon
            CHIA_SEED -> ChiaSeedIcon
            OLIVE_OIL -> OliveOilIcon
            EGGS -> EggsIcon
            GHEE -> GheeIcon
            COCOA_BUTTER -> CocoaButterIcon
        }

    override val label: String?
        @Composable get() = stringArrayResource(
            resource = Res.array.key_ketogenic_super_food_array
        )[ordinal].takeIf(
            predicate = String::isNotEmpty
        )

    override val description: String?
        @Composable get() = stringArrayResource(
            resource = Res.array.key_ketogenic_super_food_descriptions_array
        )[ordinal].takeIf(
            predicate = String::isNotEmpty
        )

    val foodName
        get() = name.replace(
            oldValue = "_",
            newValue = " "
        ).lowercase()

}