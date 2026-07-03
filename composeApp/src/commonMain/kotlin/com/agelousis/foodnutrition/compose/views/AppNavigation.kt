package com.agelousis.foodnutrition.compose.views

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay

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

