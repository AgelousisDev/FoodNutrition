package com.agelousis.kotlinmultiplatform.compose.extensions

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.agelousis.kotlinmultiplatform.utils.setupDefaultConfigs

@Composable
fun ImageLoaderConfiguration() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(
            context = context
        ).components {
            add(KtorNetworkFetcherFactory())
        }.setupDefaultConfigs().build()
    }
}