package com.agelousis.foodnutrition.network.utils

import com.yourdomain.foodnutrition.BuildKonfig

object ApiConstants {
    const val EDAMAM_BASE_URL = "https://api.edamam.com/"
    const val EDAMAM_APPLICATION_ID = "0c5147c6"
    const val EDAMAM_APPLICATION_KEY = "76f577bf212052a74d4e366b74e89537"

    object Google {
        val GEMINI_FLASH_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=${BuildKonfig.GOOGLE_AI_STUDIO_API_KEY}"
    }

}