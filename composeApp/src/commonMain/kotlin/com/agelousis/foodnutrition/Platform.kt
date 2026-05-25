package com.agelousis.foodnutrition

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform