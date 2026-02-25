package com.agelousis.kotlinmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform