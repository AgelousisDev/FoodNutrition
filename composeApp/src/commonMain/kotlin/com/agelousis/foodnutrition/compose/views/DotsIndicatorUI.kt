package com.agelousis.foodnutrition.compose.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.agelousis.foodnutrition.compose.extensions.jumpingDotTransition
import com.agelousis.foodnutrition.theme.AppTheme
import com.agelousis.foodnutrition.theme.Butterscotch
import com.agelousis.foodnutrition.theme.GraniteGrayColor
import com.agelousis.foodnutrition.theme.Steel
import com.agelousis.foodnutrition.utils.SuccessBlock

enum class DotIndicatorViewType {
    CIRCLE_DOT,
    SIMPLE_DOT
}

@Composable
fun DotsIndicatorView(
    modifier: Modifier = Modifier,
    selectedDotIndicatorViewType: DotIndicatorViewType = DotIndicatorViewType.CIRCLE_DOT,
    spaceBetween: Dp,
    dotsCount: Int,
    currentPage: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement
            .spacedBy(
                space = spaceBetween
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(
            times = dotsCount
        ) { iteration ->
            val color =
                if (currentPage == iteration)
                    Butterscotch
                else
                    Steel
            when(selectedDotIndicatorViewType) {
                DotIndicatorViewType.SIMPLE_DOT ->
                    Box(
                        modifier = Modifier
                            .clip(
                                shape = CircleShape
                            )
                            .background(
                                color = color
                            )
                            .size(
                                size = 6.dp
                            )

                    )
                DotIndicatorViewType.CIRCLE_DOT ->
                    Box(
                        modifier = Modifier
                            .then(
                                other =
                                    if (currentPage == iteration)
                                        Modifier
                                            .border(
                                                width = 1.8.dp,
                                                color = color,
                                                shape = CircleShape
                                            )
                                            .size(
                                                size = 20.dp
                                            )
                                    else
                                        Modifier
                                            .size(
                                                size = 5.dp
                                            )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(
                                    shape = CircleShape
                                )
                                .background(
                                    color = color
                                )
                                .size(
                                    size = 5.dp
                                )

                        )
                    }
            }
        }
    }
}

@Composable
fun AnimatedDotsIndicatorView(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    spaceBetween: Dp,
    dotsCount: Int,
    dotIndicatorClick: SuccessBlock<Int> = {}
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement
                .spacedBy(
                    space = spaceBetween
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(
                times = dotsCount
            ) { page ->
                Box(
                    modifier = Modifier
                        .size(
                            size = 6.dp
                        )
                        .background(
                            color = GraniteGrayColor,
                            shape = CircleShape
                        )
                        .clickable {
                            dotIndicatorClick(page)
                        }
                )
            }
        }

        Box(
            Modifier
                .jumpingDotTransition(
                    pagerState = pagerState,
                    jumpScale = 1f,
                    spacingBetweenDots = spaceBetween
                )
                .size(
                    size = 6.dp
                )
                .background(
                    color = Butterscotch,
                    shape = CircleShape,
                )
        )
    }
}

@Preview
@Composable
fun DotsIndicatorViewPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .size(
                    size = 100.dp
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        size = 16.dp
                    )
                )
        ) {
            DotsIndicatorView(
                modifier = Modifier
                    .align(
                        alignment = Alignment.Center
                    ),
                spaceBetween = 12.dp,
                dotsCount = 5,
                currentPage = 3
            )
        }
    }
}

@Preview
@Composable
fun DotsIndicatorViewPreviewInCircle() {
    AppTheme {
        Box(
            modifier = Modifier
                .size(
                    size = 100.dp
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        size = 16.dp
                    )
                )
        ) {
            DotsIndicatorView(
                modifier = Modifier
                    .align(
                        alignment = Alignment.Center
                    ),
                selectedDotIndicatorViewType = DotIndicatorViewType.CIRCLE_DOT,
                spaceBetween = 12.dp,
                dotsCount = 5,
                currentPage = 3
            )
        }
    }
}

@Preview
@Composable
fun AnimatedDotsIndicatorViewPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .size(
                    size = 100.dp
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        size = 16.dp
                    )
                )
        ) {
            AnimatedDotsIndicatorView(
                modifier = Modifier
                    .align(
                        alignment = Alignment.Center
                    ),
                pagerState = rememberPagerState(
                    initialPage = 3,
                    initialPageOffsetFraction = 0f
                ) {
                    5
                },
                spaceBetween = 12.dp,
                dotsCount = 5
            )
        }
    }
}