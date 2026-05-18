package com.agelousis.kotlinmultiplatform.compose.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.agelousis.kotlinmultiplatform.compose.models.NavigationBarAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.getValue

open class UIComposeViewModel: ViewModel() {

    // AppBar
    var appBarTitle by mutableStateOf<String?>(value = null)
    var navigationIcon by mutableStateOf<ImageVector?>(value = null)
    val navigationBarActions = mutableStateListOf<NavigationBarAction>()

    private val showDialogMutableStateFlow = MutableStateFlow(value = false)
    val showDialogStateFlow: StateFlow<Boolean> = showDialogMutableStateFlow.asStateFlow()
    var alertPair by mutableStateOf<Pair<String?, String?>>(value = null to null)

    private val showLoaderMutableStateFlow = MutableStateFlow(value = false)
    val showLoaderStateFlow: StateFlow<Boolean> = showLoaderMutableStateFlow.asStateFlow()

    private val pullToRefreshMutableStateFlow by lazy { MutableStateFlow(value = false) }
    val pullToRefreshStateFlow by lazy { pullToRefreshMutableStateFlow.asStateFlow() }

    //region SnackBar
    var snackBarMessage by mutableStateOf<String?>(
        value = null
    )
    //endregion

    var isLoading: Boolean = false
        set(value) {
            field = value
            showLoaderMutableStateFlow.value = value
        }

    fun showDialog() {
        showDialogMutableStateFlow.value = true
    }

    fun onDialogConfirm() {
        alertPair = null to null
        showDialogMutableStateFlow.value = false
        // Continue with executing the confirmed action
    }

    fun onDialogDismiss() {
        alertPair = null to null
        showDialogMutableStateFlow.value = false
    }

}