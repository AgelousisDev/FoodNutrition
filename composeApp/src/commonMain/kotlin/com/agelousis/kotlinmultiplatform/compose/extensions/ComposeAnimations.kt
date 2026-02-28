package com.agelousis.kotlinmultiplatform.compose.extensions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

fun Modifier.carouselTransition(
    pagerState: PagerState,
    page: Int
) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation =
            lerp(
                start = 0.7f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        alpha = transformation
        scaleY = transformation
    }

/*private fun Modifier.bounceDotTransition(
    pagerState: PagerState,
    jumpOffset: Float,
    jumpScale: Float,
    spacingBetweenDots: Dp
) =
    graphicsLayer {
        val targetScale = jumpScale - 1f
        val distance = size.width + spacingBetweenDots.roundToPx()
        val pageOffset = pagerState.currentPageOffsetFraction
        val scrollPosition = pagerState.currentPage + pageOffset
        val current = scrollPosition.toInt()
        val settledPage = pagerState.settledPage

        translationX = scrollPosition * distance

        val scale = if (pageOffset.absoluteValue < .5) {
            1.0f + (pageOffset.absoluteValue * 2) * targetScale;
        } else {
            jumpScale + ((1 - (pageOffset.absoluteValue * 2)) * targetScale);
        }

        scaleX = scale
        scaleY = scale

        val factor = (pageOffset.absoluteValue * Math.PI)
        val y =
            if (current >= settledPage) - sin(x = factor) * jumpOffset else sin(x = factor) * distance / 2
        translationY += y.toFloat()
    }*/

/*private fun Modifier.wormTransition(
    pagerState: PagerState,
    spacingBetweenDots: Dp
) =
    drawBehind {
        val distance = size.width + spacingBetweenDots.roundToPx()
        val scrollPosition = pagerState.currentPage + pagerState.currentPageOffsetFraction
        val wormOffset = (scrollPosition % 1) * 2

        val xPos = scrollPosition.toInt() * distance
        val head = xPos + distance * 0f.coerceAtLeast(wormOffset - 1)
        val tail = xPos + size.width + 1f.coerceAtMost(wormOffset) * distance

        val worm = RoundRect(
            left = head,
            top = 0f,
            right = tail,
            bottom = size.height,
            cornerRadius = CornerRadius(
                x = 50f
            )
        )

        val path = Path().apply {
            addRoundRect(
                roundRect = worm
            )
        }
        drawPath(path = path, color = Color.White)
    }*/

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.jumpingDotTransition(
    pagerState: PagerState,
    jumpScale: Float,
    spacingBetweenDots: Dp
) = graphicsLayer {
    val pageOffset = pagerState.currentPageOffsetFraction
    val scrollPosition = pagerState.currentPage + pageOffset
    translationX = scrollPosition * (size.width + spacingBetweenDots.roundToPx()) // 8.dp - spacing between dots

    val scale: Float
    val targetScale = jumpScale - 1f

    scale = if (pageOffset.absoluteValue < .5) {
        1.0f + (pageOffset.absoluteValue * 2) * targetScale;
    } else {
        jumpScale + ((1 - (pageOffset.absoluteValue * 2)) * targetScale);
    }

    scaleX = scale
    scaleY = scale
}

/*fun Modifier.drawAnimatedBorder(
    strokeWidth: Dp,
    shape: Shape,
    brush: (Size) -> Brush = {
        Brush.horizontalGradient(
            colors = listOf(
                WhiteTwo,
                Color.Transparent
            )
        )
    },
    durationMillis: Int
) = composed {
    val infiniteTransition = rememberInfiniteTransition(
        label = "rotation"
    )
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Modifier
        .clip(
            shape = shape
        )
        .drawWithCache {
            val strokeWidthPx = strokeWidth.toPx()
            val outline = shape.createOutline(
                size = size,
                layoutDirection = layoutDirection,
                density = this
            )
            //val pathBounds = outline.bounds

            onDrawWithContent {
                // This is actual content of the Composable that this modifier is assigned to
                drawContent()

                with(drawContext.canvas.nativeCanvas) {
                    val checkPoint = saveLayer(null, null)
                    // Destination

                    // We draw 2 times of the stroke with since we want actual size to be inside
                    // bounds while the outer stroke with is clipped with Modifier.clip

                    // 🔥 Using a maskPath with op(this, outline.path, PathOperation.Difference)
                    // And GenericShape can be used as Modifier.border does instead of clip
                    drawOutline(
                        outline = outline,
                        color = Color.Gray,
                        style = Stroke(
                            width = strokeWidthPx * 2
                        )
                    )
                    // Source
                    rotate(angle) {
                        drawCircle(
                            brush = brush(size),
                            radius = size.width,
                            blendMode = BlendMode.SrcIn,
                        )
                    }
                    restoreToCount(checkPoint)
                }
            }
        }
}*/

@Composable
fun Modifier.dropDownListAnimation(
    index: Int,
    lazyListState: LazyListState
): Modifier {
    val isOnPreview = LocalInspectionMode.current
    val animatedItems = remember {
        mutableStateMapOf<Int, Boolean>()
    }
    val isVisible by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.any { visibleItem ->
                visibleItem.index == index
            }
        }
    }
    val (itemAnimatedState, onItemAnimated) = remember {
        mutableStateOf(
            value = animatedItems[index] == true
                    || isOnPreview
        )
    }
    LaunchedEffect(
        key1 = isVisible
    ) {
        if (isVisible
            && !itemAnimatedState
        ) {
            // You can add a slight delay for a staggered effect based on index
            // delay(50L * (index % 10)) // Example staggering
            onItemAnimated(true)
            animatedItems[index] = true
        }
    }
    val translationY by animateFloatAsState(
        targetValue = if (itemAnimatedState) 0f else -50f, // Start 50dp above
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = if (itemAnimatedState) 0 else 50 * (index % 5)
        ), // Stagger based on index
        label = "itemTranslationY_$index"
    )
    val alpha by animateFloatAsState(
        targetValue = if (itemAnimatedState) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = if (itemAnimatedState) 0 else 50 * (index % 5)
        ),
        label = "itemAlpha_$index"
    )
    val rotateAnimation by animateFloatAsState(
        targetValue = if (itemAnimatedState) 0f else 180f,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = if (itemAnimatedState) 0 else 50 * (index % 5)
        ),
        label = "itemRotation_$index"
    )

    // Convert dp to pixels for translationY
    val translationYPx = with(
        receiver = LocalDensity.current
    ) Density@ {
        translationY.dp.toPx()
    }
    return graphicsLayer GraphicsLayer@ {
        this@GraphicsLayer.translationY = translationYPx
        this@GraphicsLayer.alpha = alpha
        this@GraphicsLayer.rotationX = rotateAnimation
    }
}