package com.agelousis.foodnutrition.network

import com.agelousis.foodnutrition.network.apis.EdamamAPI
import com.agelousis.foodnutrition.network.apis.createEdamamAPI
import com.agelousis.foodnutrition.network.utils.ApiConstants
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkHelper {

    private val httpClient =
        HttpClient {
            // Logging Interceptor (Equivalent to HttpLoggingInterceptor)
            install(Logging) {
                level = LogLevel.INFO
                logger = Logger.SIMPLE
            }

            // Content Negotiation (Equivalent to GsonConverterFactory)
            install(ContentNegotiation) {
                json(Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        encodeDefaults = true
                    }
                )
            }

            defaultRequest {
                header(
                    key = HttpHeaders.ContentType,
                    ContentType.Application.Json
                )
            }
        }

    val ktorfit: Ktorfit by lazy {
        Ktorfit.Builder()
            .baseUrl(ApiConstants.EDAMAM_BASE_URL)
            .httpClient(httpClient)
            .build()
    }

    val edamamApi: EdamamAPI by lazy { ktorfit.createEdamamAPI() }

}