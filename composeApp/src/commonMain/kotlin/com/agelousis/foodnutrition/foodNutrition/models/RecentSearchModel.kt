package com.agelousis.foodnutrition.foodNutrition.models

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FoodBank
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.agelousis.foodnutrition.network.repositories.SuccessBlock
import kotlinx.serialization.Serializable

@Serializable
data class RecentSearchModel(
    val title: String,
    val label: String,
    val imageUrl: String? = null,
) {

    @Composable
    private infix fun Image(
        modifier: Modifier
    ) {
        AsyncImage(
            modifier = modifier,
            model = imageUrl,
            contentDescription = title,
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    fun View(
        modifier: Modifier,
        recentSearch: SuccessBlock<RecentSearchModel>
    ) {
        val isOnPreview = LocalInspectionMode.current
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(
                size = 16.dp
            ),
            color = MaterialTheme.colorScheme.secondaryContainer,
            onClick = {
                recentSearch()
            }
        ) {
            Column(
                modifier = Modifier
                    .width(
                        width = 160.dp
                    )
                    .padding(
                        all = 8.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val imageModifier = Modifier
                    .fillMaxWidth()
                    .height(
                        height = 100.dp
                    )
                    .clip(
                        shape = RoundedCornerShape(
                            size = 12.dp
                        )
                    )
                if (isOnPreview)
                    Icon(
                        modifier = imageModifier,
                        imageVector = Icons.Outlined.FoodBank,
                        contentDescription = Icons.Outlined.FoodBank.name
                    )
                else
                    this@RecentSearchModel Image imageModifier
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
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}