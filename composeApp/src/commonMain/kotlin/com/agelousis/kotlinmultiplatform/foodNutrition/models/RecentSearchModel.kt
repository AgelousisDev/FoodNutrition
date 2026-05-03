package com.agelousis.kotlinmultiplatform.foodNutrition.models

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.agelousis.kotlinmultiplatform.theme.GraniteGrayColor

data class RecentSearchModel(
    val title: String,
    val address: String,
    val imageUrl: String = "",
) {

    @Composable
    infix fun View(
        modifier: Modifier
    ) {
        Column(
            modifier = modifier
                .width(
                    width = 160.dp
                )
        ) {
            Image(
                painter = ColorPainter(color = Color.LightGray),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        height = 100.dp
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
                    .height(
                        height = 8.dp
                    )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = address,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = GraniteGrayColor
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}