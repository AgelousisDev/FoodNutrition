package com.agelousis.kotlinmultiplatform.utils // Add this line

import androidx.compose.ui.graphics.ImageBitmap
import coil3.Image
import coil3.ImageLoader

expect fun Image.toImageBitmap(): ImageBitmap

expect fun ImageLoader.Builder.setupDefaultConfigs(): ImageLoader.Builder