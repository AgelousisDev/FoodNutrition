package com.agelousis.kotlinmultiplatform.expressiveShapes.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.expressiveShapes.extensions.ExpressiveShapesBaseActivityNavigation
import com.agelousis.kotlinmultiplatform.expressiveShapes.navigation.ExpressiveShapesNavigationScreen
import com.agelousis.kotlinmultiplatform.expressiveShapes.viewModel.ExpressiveShapesBaseViewModel
import com.agelousis.kotlinmultiplatform.utils.SuccessUnitBlock
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.key_ketogenic_super_foods_label
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpressiveShapesBaseActivityView(
    viewModel: ExpressiveShapesBaseViewModel,
    onBackPress: SuccessUnitBlock
) {
    val backStack = remember {
        mutableStateListOf<ExpressiveShapesNavigationScreen>(
            ExpressiveShapesNavigationScreen.ExpressiveShapesScreen
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = viewModel.appBarTitle
                            ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (backStack.size == 1)
                                backStack.removeLastOrNull()
                            else
                                onBackPress()
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
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    backStack.add(
                        element = ExpressiveShapesNavigationScreen.KetogenicSuperFoodsScreen
                    )
                }
            ) {
                Text(
                    text = stringResource(
                        resource = Res.string.key_ketogenic_super_foods_label
                    ),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { contentPadding ->
            Navigation(
                contentPadding = contentPadding,
                viewModel = viewModel,
                backStack = backStack
            )
        }
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Navigation(
    contentPadding: PaddingValues,
    viewModel: ExpressiveShapesBaseViewModel,
    backStack: SnapshotStateList<ExpressiveShapesNavigationScreen>
) {
    LaunchedEffect(
        key1 = backStack.size
    ) {
        backStack.lastOrNull()?.handleTopAppBar(
            viewModel = viewModel
        )
    }
    ExpressiveShapesBaseActivityNavigation(
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
            viewModel = viewModel(),
            onBackPress = {}
        )
    }
}
