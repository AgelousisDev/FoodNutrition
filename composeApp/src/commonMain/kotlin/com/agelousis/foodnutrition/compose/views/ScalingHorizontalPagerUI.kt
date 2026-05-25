package com.agelousis.foodnutrition.compose.views

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.agelousis.foodnutrition.theme.ArcticWhiteColor
import com.agelousis.foodnutrition.theme.BlueSapphire
import com.agelousis.foodnutrition.theme.DarkGreySecondary
import com.agelousis.foodnutrition.theme.GraniteGrayColor
import com.agelousis.foodnutrition.compose.util.ScalingHorizontalPagerData
import com.agelousis.foodnutrition.foodNutrition.enumerations.KetogenicSuperFood
import com.agelousis.foodnutrition.theme.AppTheme
import com.agelousis.foodnutrition.utils.SuccessBlock
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.math.absoluteValue

@Composable
fun ScalingHorizontalPagerView(
    modifier: Modifier = Modifier,
    scalingHorizontalPagerDataList: List<ScalingHorizontalPagerData>,
    pagerViewBlock: SuccessBlock<ScalingHorizontalPagerData>
) {
    val windowInfo = LocalWindowInfo.current
    val isLandscape = windowInfo.containerSize.width > windowInfo.containerSize.height
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = scalingHorizontalPagerDataList::size
    )
    LaunchedEffect(
        key1 = pagerState.currentPage
    ) {
        pagerViewBlock(
            scalingHorizontalPagerDataList[
                    pagerState.currentPage
            ]
        )
    }
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp
        )
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        coroutineScope.launch {
                            pagerState.scrollBy(
                                value = -delta
                            )
                        }
                    },
                ),
            state = pagerState,
            beyondViewportPageCount = 1,
            contentPadding = PaddingValues(
                horizontal =
                    if(!isLandscape)
                        90.dp
                    else
                        48.dp
            )
        ) { page ->
            PagerView(
                pagerState = pagerState,
                index = page,
                scalingHorizontalPagerData = scalingHorizontalPagerDataList[page]
            )
        }
        if (pagerState.pageCount > 1)
            AnimatedDotsIndicatorView(
                pagerState = pagerState,
                spaceBetween = 12.dp,
                dotsCount = scalingHorizontalPagerDataList.size
            )
    }
}

@Composable
private fun PagerView(
    pagerState: PagerState,
    index: Int,
    scalingHorizontalPagerData: ScalingHorizontalPagerData
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                // Calculate how far this page is from the current center
                val pageOffset = (
                        (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
                        ).absoluteValue

                // Apply lerp for scale (0.7f for side cards, 1f for center)
                val scale = lerp(
                    start = 0.7f,
                    stop = 1.2f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
                scaleX = scale
                scaleY = scale
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
            .padding(
                all = 24.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(
            size = 32.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 12.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(
                        size = 48.dp
                    )
                    .background(
                        color = ArcticWhiteColor,
                        shape = RoundedCornerShape(
                            size = 12.dp
                        )
                    ),
                contentAlignment = Alignment.Center

            ) {
                when(val icon = scalingHorizontalPagerData.icon) {
                    is DrawableResource ->
                        Icon(
                            painter = painterResource(
                                resource = icon
                            ),
                            contentDescription = null,
                            tint = BlueSapphire,
                            modifier = Modifier.size(24.dp)
                        )
                    is ImageVector ->
                        Icon(
                            imageVector = icon,
                            contentDescription = icon.name,
                            tint = BlueSapphire,
                            modifier = Modifier.size(24.dp)
                        )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(
                        height = 16.dp
                    )
            )

            scalingHorizontalPagerData.label?.let { label ->
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = DarkGreySecondary,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(
                modifier = Modifier
                    .height(
                        height = 8.dp
                    )
            )

            scalingHorizontalPagerData.description?.let { description ->
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = GraniteGrayColor,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ScalingHorizontalPagerViewPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        size = 16.dp
                    )
                )
                .fillMaxWidth()
                .height(
                    height = 500.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            ScalingHorizontalPagerView(
                scalingHorizontalPagerDataList = KetogenicSuperFood.entries,
                pagerViewBlock = {}
            )
        }
    }
}
