package com.agelousis.foodnutrition.utils

import androidx.compose.ui.graphics.asComposeImageBitmap
import coil3.Image
import coil3.ImageLoader
import coil3.toBitmap

actual fun Image.toImageBitmap() = this.toBitmap().asComposeImageBitmap()

actual fun ImageLoader.Builder.setupDefaultConfigs(): ImageLoader.Builder = this