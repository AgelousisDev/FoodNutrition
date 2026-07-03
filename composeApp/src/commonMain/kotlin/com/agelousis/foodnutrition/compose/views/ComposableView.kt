package com.agelousis.foodnutrition.compose.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.agelousis.foodnutrition.compose.viewModel.UIComposeViewModel

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

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun UIComposeViewModel.Loader() {
    val isLoading by showLoaderStateFlow.collectAsState()

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(
                        alpha = 0.3f
                    )
                )
                .pointerInput(
                    key1 = Unit
                ) {},
            contentAlignment = Alignment.Center
        ) {
            CircularWavyProgressIndicator(
                modifier = Modifier
                    .size(
                        size = 48.dp
                    ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
infix fun UIComposeViewModel.SnackBarMessage(
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(
        key1 = snackBarMessage
    ) {
        if (!snackBarMessage.isNullOrEmpty()) {
            snackBarHostState.showSnackbar(
                message = snackBarMessage
                    ?: return@LaunchedEffect
            )
            snackBarMessage = null
        }
    }
}