package com.agelousis.kotlinmultiplatform.foodNutrition.enumerations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewModelScope
import com.agelousis.kotlinmultiplatform.compose.models.NavigationBarAction
import com.agelousis.kotlinmultiplatform.theme.AvocadoIcon
import com.agelousis.kotlinmultiplatform.foodNutrition.navigation.FoodNutritionNavigationScreen
import com.agelousis.kotlinmultiplatform.foodNutrition.viewModel.FoodNutritionBaseViewModel
import com.agelousis.kotlinmultiplatform.utils.ShareManager
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

    suspend fun action(
        viewModel: FoodNutritionBaseViewModel,
        backStack: SnapshotStateList<FoodNutritionNavigationScreen>,
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
                backStack.add(
                    element = FoodNutritionNavigationScreen.KetogenicSuperFoodsScreen
                )
        }
    }

}