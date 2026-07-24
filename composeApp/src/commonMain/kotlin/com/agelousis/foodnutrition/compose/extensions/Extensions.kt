package com.agelousis.foodnutrition.compose.extensions

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

val LazyListState.rememberLazyListScrollY
    @Composable get() = remember(
        key1 = this
    ) {
        derivedStateOf {
            val layoutInfo = layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo

            if (visibleItemsInfo.isEmpty()) {
                0
            } else {
                val firstVisibleItem = visibleItemsInfo.first()
                val firstVisibleItemIndex = firstVisibleItem.index
                val firstVisibleItemScrollOffset = -firstVisibleItem.offset

                // Note: This assumes all items have the same height.
                // If items have different heights, this provides a close estimate.
                val estimatedItemHeight = firstVisibleItem.size

                (firstVisibleItemIndex * estimatedItemHeight) + firstVisibleItemScrollOffset
            }
        }
    }.value