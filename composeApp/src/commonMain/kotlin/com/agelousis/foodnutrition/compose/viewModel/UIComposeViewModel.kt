package com.agelousis.foodnutrition.compose.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class UIComposeViewModel: ViewModel() {

    private val showDialogMutableStateFlow = MutableStateFlow(value = false)
    val showDialogStateFlow: StateFlow<Boolean> = showDialogMutableStateFlow.asStateFlow()
    var alertPair by mutableStateOf<Pair<String?, String?>>(value = null to null)

    private val showLoaderMutableStateFlow = MutableStateFlow(value = false)
    val showLoaderStateFlow: StateFlow<Boolean> = showLoaderMutableStateFlow.asStateFlow()

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