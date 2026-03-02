package com.agelousis.kotlinmultiplatform.expressiveShapes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agelousis.kotlinmultiplatform.compose.views.ScalingHorizontalPagerView
import com.agelousis.kotlinmultiplatform.expressiveShapes.enumerations.KetogenicSuperFood

@Composable
fun KetogenicSuperFoodsScreenView(
    modifier: Modifier = Modifier
) {
    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
    val isOnPreview = LocalInspectionMode.current
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            bottom = if (isOnPreview) 24.dp else navigationBarsPadding.calculateBottomPadding()
        )
    ) {
        item {
            ScalingHorizontalPagerView(
                modifier = Modifier
                    .animateItem()
                    .padding(
                        top = 24.dp
                    ),
                scalingHorizontalPagerDataList = KetogenicSuperFood.entries
            )
        }
    }
}

@Preview
@Composable
fun KetogenicSuperFoodsScreenViewPreview() {
    MaterialTheme {
        KetogenicSuperFoodsScreenView(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        size = 16.dp
                    )
                )
        )
    }
}
