package com.agelousis.foodnutrition.compose.views

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.agelousis.foodnutrition.network.repositories.SuccessUnitBlock
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_ok
import org.jetbrains.compose.resources.stringResource

@Composable
fun SimpleDialog(
    show: Boolean,
    title: String,
    message: String?,
    confirmButtonText: String = stringResource(
        resource = Res.string.key_ok
    ),
    confirmButton: SuccessUnitBlock = {},
    cancelButtonText: String? = null,
    cancelButton: SuccessUnitBlock = {},
    dismissBlock: SuccessUnitBlock = {}
) {
    if (show)
        AlertDialog(
            onDismissRequest = dismissBlock,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Text(
                    text = message
                        ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(
                    onClick = confirmButton
                ) {
                    Text(
                        text = confirmButtonText,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            },
            dismissButton = {
                cancelButtonText?.let { button ->
                    TextButton(
                        onClick = cancelButton
                    ) {
                        Text(
                            text = button,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            },
            shape = RoundedCornerShape(
                size = 16.dp
            )
        )
}