package com.agelousis.kotlinmultiplatform.utils

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import coil3.Image
import coil3.toBitmap

actual fun Image.toImageBitmap(): ImageBitmap {
    val bitmap = toBitmap()
    return if (bitmap.config == Bitmap.Config.HARDWARE)
        bitmap.copy(Bitmap.Config.ARGB_8888, false).asImageBitmap()
    else
        bitmap.asImageBitmap()
}