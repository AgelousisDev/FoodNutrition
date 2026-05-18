package com.agelousis.kotlinmultiplatform.utils // Add this line

import androidx.compose.ui.graphics.ImageBitmap
import coil3.Image

expect fun Image.toImageBitmap(): ImageBitmap