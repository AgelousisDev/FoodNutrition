package com.agelousis.kotlinmultiplatform.compose.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.agelousis.kotlinmultiplatform.compose.theme.ArcticWhiteColor
import com.agelousis.kotlinmultiplatform.compose.theme.BlueSapphire
import com.agelousis.kotlinmultiplatform.compose.theme.DarkGreySecondary
import com.agelousis.kotlinmultiplatform.compose.theme.GraniteGrayColor
import com.agelousis.kotlinmultiplatform.compose.util.ScalingHorizontalPagerData
import com.agelousis.kotlinmultiplatform.expressiveShapes.enumerations.KetogenicSuperFood
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.math.absoluteValue

@Composable
fun ScalingHorizontalPagerView(
    modifier: Modifier = Modifier,
    scalingHorizontalPagerDataList: List<ScalingHorizontalPagerData>
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = scalingHorizontalPagerDataList::size
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth(),
            state = pagerState,
            contentPadding = PaddingValues(
                horizontal = 90.dp
            ),
            pageSpacing = 0.dp
        ) { page ->
            PagerView(
                pagerState = pagerState,
                index = page,
                scalingHorizontalPagerData = scalingHorizontalPagerDataList[page]
            )
        }
        if (pagerState.pageCount > 1)
            AnimatedDotsIndicatorView(
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        top = 24.dp,
                        end = 24.dp
                    ),
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

                // Apply lerp for scale (0.85f for side cards, 1f for center)
                val scale = lerp(
                    start = 0.7f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
                scaleX = scale
                scaleY = scale
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp
            )
        ) {
            Box(
                modifier = Modifier
                    .size(
                        size = 36.dp
                    )
                    .background(
                        color = ArcticWhiteColor,
                        shape = RoundedCornerShape(
                            size = 8.dp
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
                            tint = BlueSapphire
                        )
                    is ImageVector ->
                        Icon(
                            imageVector = icon,
                            contentDescription = icon.name,
                            tint = BlueSapphire
                        )
                }
            }
            //val (title, description, button) = additionalBocProduct data resources
            scalingHorizontalPagerData.label?.let { label ->
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        color = DarkGreySecondary,
                        textAlign = TextAlign.Center
                    )
                )
            }
            scalingHorizontalPagerData.description?.let { description ->
                Text(
                    text = description,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = GraniteGrayColor,
                        textAlign = TextAlign.Center
                    )
                )
            }
            /*TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = ArcticWhiteColor
                ),
                shape = RoundedCornerShape(
                    size = 12.dp
                ),
                onClick = {
                    additionalBocProductSelection(
                        scalingHorizontalPagerData
                    )
                }
            ) {
                Text(
                    text = button,
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Medium,
                        color = BlueSapphire
                    )
                )
            }*/
        }
    }
}

@Preview
@Composable
fun ScalingHorizontalPagerViewPreview() {
    MaterialTheme {
        ScalingHorizontalPagerView(
            scalingHorizontalPagerDataList = KetogenicSuperFood.entries
        )
    }
}