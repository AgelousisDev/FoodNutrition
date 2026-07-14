package com.agelousis.foodnutrition.foodNutrition.enumerations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewModelScope
import com.agelousis.foodnutrition.compose.models.NavigationBarAction
import com.agelousis.foodnutrition.theme.AvocadoIcon
import com.agelousis.foodnutrition.foodNutrition.navigation.FoodNutritionNavigationScreen
import com.agelousis.foodnutrition.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.foodnutrition.utils.ShareManager
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_copied_clipboard_label
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

enum class FoodNutritionBaseActivityNavigationBarAction: NavigationBarAction {
    SHARE,
    KETOGENIC_SUPER_FOODS;

    override val icon: ImageVector?
        get() = when(this) {
            SHARE ->
                Icons.Outlined.Share
            KETOGENIC_SUPER_FOODS ->
                AvocadoIcon
        }

    //backStack:

    fun action(
        viewModel: FoodNutritionBaseViewModel,
        shareManager: ShareManager,
        data: Any?
    ) {
        when(this) {
            SHARE ->
                viewModel.viewModelScope.launch {
                    shareManager.share(
                        text = data as? String
                            ?: return@launch,
                        completion = {
                            viewModel.viewModelScope.launch {
                                viewModel.snackBarMessage = getString(
                                    resource = Res.string.key_copied_clipboard_label
                                )
                            }
                        }
                    )
                }
            KETOGENIC_SUPER_FOODS ->
                viewModel.navigationScreens.add(
                    element = FoodNutritionNavigationScreen.KetogenicSuperFoodsScreen
                )
        }
    }

}