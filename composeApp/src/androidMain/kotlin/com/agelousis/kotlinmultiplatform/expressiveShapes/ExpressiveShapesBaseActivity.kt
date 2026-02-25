package com.agelousis.kotlinmultiplatform.expressiveShapes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agelousis.kotlinmultiplatform.expressiveShapes.ui.ExpressiveShapesBaseActivityView

class ExpressiveShapesBaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ExpressiveShapesBaseActivityView(
                    viewModel = viewModel()
                )
            }
        }
    }
}