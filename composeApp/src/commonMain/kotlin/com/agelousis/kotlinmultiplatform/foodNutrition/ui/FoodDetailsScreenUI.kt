package com.agelousis.kotlinmultiplatform.foodNutrition.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agelousis.kotlinmultiplatform.theme.Begonia
import com.agelousis.kotlinmultiplatform.theme.Butterscotch
import com.agelousis.kotlinmultiplatform.theme.GraniteGrayColor
import com.agelousis.kotlinmultiplatform.theme.Jasmine
import com.agelousis.kotlinmultiplatform.theme.LightPurple
import com.agelousis.kotlinmultiplatform.compose.views.DotsIndicatorView
import com.agelousis.kotlinmultiplatform.network.response.INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
import com.agelousis.kotlinmultiplatform.network.response.IngredientsDataResponseModel
import com.agelousis.kotlinmultiplatform.theme.AppTheme
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_bookmark_label
import kotlinmultiplatform.composeapp.generated.resources.key_free_delivery_label
import kotlinmultiplatform.composeapp.generated.resources.key_photo_label
import kotlinmultiplatform.composeapp.generated.resources.key_ratings_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun FoodDetailsScreenView(
    modifier: Modifier = Modifier,
    ingredientsDataResponseModel: IngredientsDataResponseModel
) {
    val pagerState = rememberPagerState { 3 }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // Image Slider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                Image(
                    painter = ColorPainter(Color.LightGray),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Top Bar Icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    onClick = {},
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Box(modifier = Modifier.size(20.dp).background(Color.Black, CircleShape))
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(
                        onClick = {},
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.5f),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Box(modifier = Modifier.size(20.dp).background(Color.Black, CircleShape))
                        }
                    }
                    Surface(
                        onClick = {},
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.5f),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Box(modifier = Modifier.size(20.dp).background(Color.Black, CircleShape))
                        }
                    }
                }
            }

            // Dots Indicator
            DotsIndicatorView(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp),
                spaceBetween = 8.dp,
                dotsCount = 3,
                currentPage = pagerState.currentPage
            )
        }

        // Restaurant Info Card
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-24).dp),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Chocolat' N Spice",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(16.dp).background(GraniteGrayColor, CircleShape))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "03 Jameson Manors Apt. 177",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    BadgeItem(text = stringResource(Res.string.key_free_delivery_label), backgroundColor = Butterscotch)
                    BadgeItem(text = "33 min", backgroundColor = Color.Transparent, textColor = Color.Gray)
                    BadgeItem(text = "27 miles", backgroundColor = Color.Transparent, textColor = Color.Gray)
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Stats Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DetailStatItem(
                        icon = { Box(modifier = Modifier.size(32.dp).background(Jasmine, CircleShape)) },
                        label = stringResource(Res.string.key_ratings_label),
                        value = "4.5"
                    )
                    DetailStatItem(
                        icon = { Box(modifier = Modifier.size(32.dp).background(Begonia, CircleShape)) },
                        label = stringResource(Res.string.key_bookmark_label),
                        value = "137k"
                    )
                    DetailStatItem(
                        icon = { Box(modifier = Modifier.size(32.dp).background(LightPurple, CircleShape)) },
                        label = stringResource(Res.string.key_photo_label),
                        value = "346"
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "From the French countryside, to your doorstep. PAUL was founded in 1889 as a family bakery and patisserie. Savour a selection of viennoiserie (croissants etc.)...",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        lineHeight = 24.sp,
                        color = Color.DarkGray
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = stringResource(Res.string.key_photo_label),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(5) {
                        Image(
                            painter = ColorPainter(Color.LightGray),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BadgeItem(text: String, backgroundColor: Color, textColor: Color = Color.White) {
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(8.dp),
        border = if (backgroundColor == Color.Transparent) BorderStroke(1.dp, Color.LightGray) else null
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.bodySmall.copy(
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun DetailStatItem(icon: @Composable () -> Unit, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        icon()
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }
    }
}

@Preview
@Composable
fun FoodDetailsScreenViewPreview() {
    AppTheme {
        FoodDetailsScreenView(
            ingredientsDataResponseModel = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
                ?: return@AppTheme
        )
    }
}

@Preview(widthDp = 1200, heightDp = 800)
@Composable
fun FoodDetailsScreenViewInLandscapePreview() {
    AppTheme {
        FoodDetailsScreenView(
            ingredientsDataResponseModel = INGREDIENTS_DATA_RESPONSE_MOCK_MODEL
                ?: return@AppTheme
        )
    }
}
