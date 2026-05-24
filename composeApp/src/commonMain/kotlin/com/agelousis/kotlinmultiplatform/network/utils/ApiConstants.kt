package com.agelousis.kotlinmultiplatform.network.utils

object ApiConstants {
    const val EDAMAM_BASE_URL = "https://api.edamam.com/"
    const val EDAMAM_APPLICATION_ID = "0c5147c6"
    const val EDAMAM_APPLICATION_KEY = "76f577bf212052a74d4e366b74e89537"

    object Google {
        private const val GOOGLE_AI_STUDIO_API_KEY = "AIzaSyDf9vG6sFGB7X683U-7Ob2a2PKrnSJfDXo"
        const val GOOGLE_AI_STUDIO_PROJECT_NUMBER = "952730042166"
        const val GEMINI_FLASH_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=$GOOGLE_AI_STUDIO_API_KEY"
    }

}