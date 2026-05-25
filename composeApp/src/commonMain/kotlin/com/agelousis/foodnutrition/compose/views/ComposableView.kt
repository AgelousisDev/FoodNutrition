package com.agelousis.foodnutrition.compose.views

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
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
fun <T: Any> AppNavigation(
    contentPadding: PaddingValues = PaddingValues(),
    backStack: SnapshotStateList<T>,
    entryProvider: (key: T) -> NavEntry<T>
) {
    NavDisplay(
        modifier = Modifier
            .padding(
                top = contentPadding.calculateTopPadding()
            ),
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        transitionSpec = {
            // Forward navigation: Slide from right to left
            (fadeIn(animationSpec = tween(300)) +
                    slideInHorizontally(animationSpec = tween(400)) { it / 2 } +
                    scaleIn(initialScale = 0.9f, animationSpec = tween(400)))
                .togetherWith(
                    fadeOut(animationSpec = tween(300)) +
                            slideOutHorizontally(animationSpec = tween(400)) { -it / 2 }
                )
        },
        popTransitionSpec = {
            // Backwards navigation: Slide from left to right
            (fadeIn(animationSpec = tween(300)) +
                    slideInHorizontally(animationSpec = tween(400)) { -it / 2 })
                .togetherWith(
                    fadeOut(animationSpec = tween(300)) +
                            slideOutHorizontally(animationSpec = tween(400)) { it / 2 } +
                            scaleOut(targetScale = 0.9f, animationSpec = tween(400))
                )
        },
        predictivePopTransitionSpec = {
            // Predictive back (e.g. gesture navigation)
            // Usually matches pop transition but handles the swipe edge
            (fadeIn() + slideInHorizontally { -it / 2 })
                .togetherWith(fadeOut() + slideOutHorizontally { it / 2 })
        },
        entryProvider = entryProvider
    )
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