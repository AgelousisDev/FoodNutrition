package com.agelousis.kotlinmultiplatform.expressiveShapes.extensions

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.agelousis.kotlinmultiplatform.expressiveShapes.ExpressiveShapesBaseActivity
import com.agelousis.kotlinmultiplatform.expressiveShapes.navigation.ExpressiveShapesNavigationScreen
import com.agelousis.kotlinmultiplatform.expressiveShapes.ui.ExpressiveShapesScreenView
import com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel.ExpressiveShapesBaseViewModel

@Composable
fun ExpressiveShapesBaseActivity.ExpressiveShapesBaseActivityNavigation(
    contentPadding: PaddingValues,
    viewModel: ExpressiveShapesBaseViewModel,
    backStack: SnapshotStateList<ExpressiveShapesNavigationScreen>
) {
    NavDisplay(
        modifier = Modifier
            .padding(
                paddingValues = contentPadding
            ),
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = entryProvider {
            entry<ExpressiveShapesNavigationScreen.ExpressiveShapesScreen> {
                ExpressiveShapesScreenView()
            }
        }
    )
}