package com.agelousis.kotlinmultiplatform.utils

import com.agelousis.kotlinmultiplatform.network.utils.ApiConstants
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodeURLParameter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

object AIImageManager {
    private val client = HttpClient()

    suspend infix fun generateImageFromKeyword(
        keyword: String
    ) = try {
        val response = client.post(
            urlString = ApiConstants.Google.GEMINI_FLASH_URL
        ) {
            contentType(
                type = ContentType.Application.Json
            )
            setBody(
                body = buildJsonObject {
                    putJsonArray(
                        key = "contents"
                    ) {
                        addJsonObject {
                            putJsonArray(
                                key = "parts"
                            ) {
                                addJsonObject {
                                    put(
                                        key = "text",
                                        value = "Give me a single, short, highly detailed professional food photography prompt for: $keyword. Focus on high-end lighting, HD resolution, and studio quality. Output ONLY the prompt text."
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }

        // Extract the prompt from Gemini's JSON response
        val jsonResponse = Json.parseToJsonElement(
            string = response.bodyAsText()
        ).jsonObject
        val aiPrompt = jsonResponse["candidates"]?.jsonArray?.getOrNull(
            index = 0
        )
            ?.jsonObject?.get(
                key = "content"
            )
            ?.jsonObject?.get(
                key = "parts"
            )
            ?.jsonArray?.getOrNull(
                index = 0
            )
            ?.jsonObject?.get(
                key = "text"
            )
            ?.jsonPrimitive?.content ?: keyword

        val encodedPrompt = aiPrompt.encodeURLParameter()
        "https://image.pollinations.ai/prompt/$encodedPrompt?width=1024&height=1024&nologo=true"
    } catch (_: Exception) {
        "https://image.pollinations.ai/prompt/${keyword.encodeURLParameter()}?width=1024&height=1024&nologo=true"
    }
}