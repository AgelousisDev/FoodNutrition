package com.agelousis.kotlinmultiplatform.foodNutrition.models

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.agelousis.kotlinmultiplatform.theme.Butterscotch
import com.agelousis.kotlinmultiplatform.theme.GraniteGrayColor
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_ratings_label
import org.jetbrains.compose.resources.stringResource

data class RecommendationModel(
    val title: String,
    val address: String,
    val rating: Double,
    val reviewsCount: Int,
    val imageUrl: String = "",
) {
    @Composable
    infix fun View(
        modifier: Modifier
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = ColorPainter(color = Color.LightGray),
                contentDescription = null,
                modifier = Modifier
                    .size(
                        size = 80.dp
                    )
                    .clip(
                        shape = RoundedCornerShape(
                            size = 12.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )
            Spacer(
                modifier = Modifier
                    .width(
                        width = 16.dp
                    )
            )
            Column(
                modifier = Modifier
                    .weight(
                        weight = 1f
                    )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = address,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = GraniteGrayColor
                    )
                )
                Spacer(
                    modifier = Modifier
                        .height(
                            height = 4.dp
                        )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(
                                size = 12.dp
                            )
                            .background(
                                color = Butterscotch,
                                shape = CircleShape
                            )
                    )
                    Spacer(
                        modifier = Modifier
                            .width(
                                width = 4.dp
                            )
                    )
                    Text(
                        text = "$rating ($reviewsCount ${
                            stringResource(
                                resource = Res.string.key_ratings_label
                            )
                        })",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = GraniteGrayColor
                        )
                    )
                }
            }
        }
    }
}