package com.agelousis.kotlinmultiplatform.network

import com.agelousis.kotlinmultiplatform.network.utils.ApiConstants
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkHelper {

    private val httpClient =
        HttpClient {
            // Logging Interceptor (Equivalent to HttpLoggingInterceptor)
            install(
                plugin = Logging
            ) {
                level = LogLevel.BODY
                logger = Logger.DEFAULT
            }

            // Content Negotiation (Equivalent to GsonConverterFactory)
            install(
                plugin = ContentNegotiation
            ) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        encodeDefaults = true
                    }
                )
            }
        }

    val ktorfit: Ktorfit by lazy {
        Ktorfit.Builder()
            .baseUrl(ApiConstants.EDAMAM_BASE_URL)
            .httpClient(httpClient)
            .build()
    }

}