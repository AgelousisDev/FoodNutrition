package com.agelousis.kotlinmultiplatform.expressiveShapes.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.expressiveShapes.ExpressiveShapesBaseActivity
import com.agelousis.kotlinmultiplatform.expressiveShapes.extensions.TransactionHistoryManagementBaseActivityNavigation
import com.agelousis.kotlinmultiplatform.expressiveShapes.navigation.ExpressiveShapesNavigationScreen
import com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel.ExpressiveShapesBaseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpressiveShapesBaseActivityView(
    viewModel: ExpressiveShapesBaseViewModel
) {
    val backStack = remember {
        mutableStateListOf<ExpressiveShapesNavigationScreen>(
            ExpressiveShapesNavigationScreen.ExpressiveShapesScreen
        )
    }
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = viewModel.appBarTitle
                            ?: "",
                        style = MaterialTheme.typography.displayLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = viewModel.navigationIcon
                                ?: Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = viewModel.navigationIcon?.name,
                        )
                    }
                }
            )
        },
        content = { contentPadding ->
            TransactionHistoryManagementBaseNavigation(
                contentPadding = contentPadding,
                viewModel = viewModel,
                backStack = backStack
            )
        }
    )
}

@Composable
private fun TransactionHistoryManagementBaseNavigation(
    contentPadding: PaddingValues,
    viewModel: ExpressiveShapesBaseViewModel,
    backStack: SnapshotStateList<ExpressiveShapesNavigationScreen>
) {
    val context = LocalContext.current
    LaunchedEffect(
        key1 = backStack.size
    ) {
        backStack.lastOrNull()?.handleTopAppBar(
            context = context,
            viewModel = viewModel
        )
    }
    (context as? ExpressiveShapesBaseActivity)?.TransactionHistoryManagementBaseActivityNavigation(
        contentPadding = contentPadding,
        viewModel = viewModel,
        backStack = backStack
    )
}

@Preview
@Composable
fun ExpressiveShapesBaseActivityViewPreview() {
    MaterialTheme {
        ExpressiveShapesBaseActivityView(
            viewModel = viewModel()
        )
    }
}
