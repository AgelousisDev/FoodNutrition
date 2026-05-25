package com.agelousis.foodnutrition.foodNutrition.ui.alert

import androidx.compose.runtime.Composable
import com.agelousis.foodnutrition.compose.views.SimpleDialog
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_cancel_label
import kotlinmultiplatform.composeapp.generated.resources.key_clear_recent_search_alert_text
import kotlinmultiplatform.composeapp.generated.resources.key_ok_label
import kotlinmultiplatform.composeapp.generated.resources.key_recent_search_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun ClearRecentSearchAlert(
    state: Boolean,
    confirmBlock: () -> Unit,
    cancelButton: () -> Unit,
) {
    SimpleDialog(
        show = state,
        title = stringResource(
            resource = Res.string.key_recent_search_label
        ),
        message = stringResource(
            resource = Res.string.key_clear_recent_search_alert_text
        ),
        confirmButtonText = stringResource(
            resource = Res.string.key_ok_label
        ),
        confirmButton = confirmBlock,
        cancelButtonText = stringResource(
            resource = Res.string.key_cancel_label
        ),
        cancelButton = cancelButton,
        dismissBlock = cancelButton
    )
}