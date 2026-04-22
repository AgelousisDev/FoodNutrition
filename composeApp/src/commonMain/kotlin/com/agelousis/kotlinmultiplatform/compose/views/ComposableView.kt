package com.agelousis.kotlinmultiplatform.compose.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.agelousis.kotlinmultiplatform.compose.viewModel.UIComposeViewModel

@Composable
fun UIComposeViewModel.ErrorMessage() {
    val showDialog by showDialogStateFlow.collectAsState()
    SimpleDialog(
        show = showDialog,
        title = alertPair.first ?: "",
        message = alertPair.second,
        confirmButton = ::onDialogConfirm
    )
}