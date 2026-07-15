package com.agelousis.foodnutrition.foodNutrition.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agelousis.foodnutrition.foodNutrition.models.RecentSearchModel
import com.agelousis.foodnutrition.network.repositories.SuccessBlock
import com.agelousis.foodnutrition.theme.AppTheme

@Composable
fun RecentSearchItemsView(
    modifier: Modifier = Modifier,
    recentSearches: List<RecentSearchModel>,
    foodDetailsRedirection: SuccessBlock<String>
) {
    val screenWidth = LocalWindowInfo.current.containerDpSize.width
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment =
                if (recentSearches.size.rem(
                        other = 2
                    ) == 1)
                    Alignment.Start
                else
                    Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        maxItemsInEachRow = 2
    ) {
        recentSearches.reversed().forEach { recentSearchModel ->
            recentSearchModel.View(
                modifier = Modifier
                    .width(
                        width = (screenWidth / 2) - 32.dp
                    ),
                recentSearch = RecentSearchModel@ {
                    foodDetailsRedirection(
                        this@RecentSearchModel.title
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun RecentSearchItemsViewPreview() {
    AppTheme {
        RecentSearchItemsView(
            recentSearches = listOf(
                RecentSearchModel(
                    title = "Andy & Cindy's Diner",
                    label = "22 Powlowski Plains"
                ),
                RecentSearchModel(
                    title = "Gado & Grill",
                    label = "78 Schultz Cape Apt. 132"
                )
            ),
            foodDetailsRedirection = {}
        )
    }
}