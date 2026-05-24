package com.agelousis.kotlinmultiplatform.compose.extensions

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.agelousis.kotlinmultiplatform.utils.setupDefaultConfigs
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout

@Composable
fun ImageLoaderConfiguration() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(
            context = context
        ).components {
            add(
                factory = KtorNetworkFetcherFactory()
            )
            add(
                factory = KtorNetworkFetcherFactory(
                    httpClient = HttpClient {
                        install(
                            plugin = HttpTimeout
                        ) {
                            socketTimeoutMillis = 60_000
                            connectTimeoutMillis = 60_000
                            requestTimeoutMillis = 60_000
                        }
                    }
                )
            )
        }.setupDefaultConfigs().build()
    }
}