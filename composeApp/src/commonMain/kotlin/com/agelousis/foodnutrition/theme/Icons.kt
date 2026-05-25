package com.agelousis.foodnutrition.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val AvocadoIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Avocado",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).path(fill = SolidColor(Color.Black)) {
        moveTo(12f, 3f)
        curveTo(8.5f, 3f, 6f, 7f, 6f, 11f)
        curveTo(6f, 16.5f, 8.5f, 21f, 12f, 21f)
        reflectiveCurveTo(18f, 16.5f, 18f, 11f)
        curveTo(18f, 7f, 15.5f, 3f, 12f, 3f)
        close()
        moveTo(12f, 12f)
        arcTo(3f, 4f, 0f, true, true, 12f, 20f)
        arcTo(3f, 4f, 0f, true, true, 12f, 12f)
        close()
    }.build()

val FlaxSeedIcon: ImageVector
    get() = ImageVector.Builder(
        name = "FlaxSeed",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).path(fill = SolidColor(Color.Black)) {
        moveTo(10f, 6f)
        curveTo(10f, 6f, 7f, 9f, 7f, 11f)
        arcTo(3f, 3f, 0f, true, false, 13f, 11f)
        curveTo(13f, 9f, 10f, 6f, 10f, 6f)
        close()
        moveTo(16f, 12f)
        curveTo(16f, 12f, 14f, 14f, 14f, 15.5f)
        arcTo(2f, 2f, 0f, true, false, 18f, 15.5f)
        curveTo(18f, 14f, 16f, 12f, 16f, 12f)
        close()
    }.build()

val ChiaSeedIcon: ImageVector
    get() = ImageVector.Builder(
        name = "ChiaSeed",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).path(fill = SolidColor(Color.Black)) {
        // Using multiple small paths for dots
        moveTo(8f, 8f)
        arcTo(1.5f, 1.5f, 0f, true, true, 8f, 11f)
        arcTo(1.5f, 1.5f, 0f, true, true, 8f, 8f)
        close()
        moveTo(12f, 10f)
        arcTo(1f, 1f, 0f, true, true, 12f, 12f)
        arcTo(1f, 1f, 0f, true, true, 12f, 10f)
        close()
        moveTo(15f, 7f)
        arcTo(1.2f, 1.2f, 0f, true, true, 15f, 9.4f)
        arcTo(1.2f, 1.2f, 0f, true, true, 15f, 7f)
        close()
        moveTo(9f, 14f)
        arcTo(1.3f, 1.3f, 0f, true, true, 9f, 16.6f)
        arcTo(1.3f, 1.3f, 0f, true, true, 9f, 14f)
        close()
        moveTo(14f, 16f)
        arcTo(1.1f, 1.1f, 0f, true, true, 14f, 18.2f)
        arcTo(1.1f, 1.1f, 0f, true, true, 14f, 16f)
        close()
        moveTo(11f, 19f)
        arcTo(1.4f, 1.4f, 0f, true, true, 11f, 21.8f)
        arcTo(1.4f, 1.4f, 0f, true, true, 11f, 19f)
        close()
    }.build()

val OliveOilIcon: ImageVector
    get() = ImageVector.Builder(
        name = "OliveOil",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).path(fill = SolidColor(Color.Black)) {
        moveTo(10f, 3f)
        horizontalLineTo(14f)
        verticalLineTo(5f)
        horizontalLineTo(10f)
        close()
        moveTo(9f, 6f)
        horizontalLineTo(15f)
        curveTo(17f, 6f, 18f, 7f, 18f, 9f)
        verticalLineTo(19f)
        curveTo(18f, 21f, 17f, 22f, 15f, 22f)
        horizontalLineTo(9f)
        curveTo(7f, 22f, 6f, 21f, 6f, 19f)
        verticalLineTo(9f)
        curveTo(6f, 7f, 7f, 6f, 9f, 6f)
        close()
        // Simple oil drop shape inside bottle
        moveTo(12f, 11f)
        curveTo(10.5f, 11f, 9f, 13f, 9f, 15f)
        arcTo(3f, 3f, 0f, true, false, 15f, 15f)
        curveTo(15f, 13f, 13.5f, 11f, 12f, 11f)
        close()
    }.build()

val EggsIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Eggs",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).path(fill = SolidColor(Color.Black)) {
        moveTo(10f, 4f)
        curveTo(7f, 4f, 5f, 8f, 5f, 12f)
        reflectiveCurveTo(7f, 18f, 10f, 18f)
        reflectiveCurveTo(15f, 14f, 15f, 10f)
        reflectiveCurveTo(13f, 4f, 10f, 4f)
        close()
        moveTo(16f, 10f)
        curveTo(14f, 10f, 13f, 13f, 13f, 16f)
        reflectiveCurveTo(14f, 21f, 16f, 21f)
        reflectiveCurveTo(19f, 18f, 19f, 14f)
        reflectiveCurveTo(18f, 10f, 16f, 10f)
        close()
    }.build()

val GheeIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Ghee",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).path(fill = SolidColor(Color.Black)) {
        moveTo(7f, 4f)
        horizontalLineTo(17f)
        verticalLineTo(6f)
        horizontalLineTo(7f)
        close()
        moveTo(6f, 7f)
        verticalLineTo(20f)
        curveTo(6f, 21f, 7f, 22f, 8f, 22f)
        horizontalLineTo(16f)
        curveTo(17f, 22f, 18f, 21f, 18f, 20f)
        verticalLineTo(7f)
        horizontalLineTo(6f)
        close()
        // Scoop of ghee
        moveTo(12f, 10f)
        curveTo(10f, 10f, 8f, 12f, 8f, 15f)
        horizontalLineTo(16f)
        curveTo(16f, 12f, 14f, 10f, 12f, 10f)
        close()
    }.build()

val CocoaButterIcon: ImageVector
    get() = ImageVector.Builder(
        name = "CocoaButter",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).path(fill = SolidColor(Color.Black)) {
        moveTo(4f, 8f)
        horizontalLineTo(12f)
        verticalLineTo(16f)
        horizontalLineTo(4f)
        close()
        moveTo(14f, 4f)
        horizontalLineTo(20f)
        verticalLineTo(10f)
        horizontalLineTo(14f)
        close()
        moveTo(13f, 12f)
        horizontalLineTo(19f)
        verticalLineTo(18f)
        horizontalLineTo(13f)
        close()
    }.build()
