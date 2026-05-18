package com.agelousis.kotlinmultiplatform.utils

import androidx.compose.ui.graphics.asComposeImageBitmap
import coil3.Image
import coil3.toBitmap

actual fun Image.toImageBitmap() = this.toBitmap().asComposeImageBitmap()