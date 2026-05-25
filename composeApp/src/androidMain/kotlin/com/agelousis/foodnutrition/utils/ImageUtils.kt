package com.agelousis.foodnutrition.utils

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import coil3.Image
import coil3.ImageLoader
import coil3.request.allowHardware
import coil3.toBitmap

actual fun Image.toImageBitmap(): ImageBitmap =
    this.toBitmap(
        config = Bitmap.Config.ARGB_8888,
        width = this.width,
        height = this.height
    ).asImageBitmap()

actual fun ImageLoader.Builder.setupDefaultConfigs() = this.allowHardware(enable = false)