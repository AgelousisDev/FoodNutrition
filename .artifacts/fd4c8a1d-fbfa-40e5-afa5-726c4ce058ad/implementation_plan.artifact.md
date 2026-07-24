# Fix Ktorfit ClassCastException by Aligning Kotlin and Plugin Versions

The project is currently experiencing a `java.lang.ClassCastException` during the Kotlin compilation of the `:composeApp` module. This error is caused by a binary incompatibility between the Ktorfit compiler plugin and the Kotlin version being used. Specifically, Ktorfit 2.7.3 requires Kotlin 2.2.0 or higher, but the project is currently using Kotlin 2.1.20.

## User Review Required

> [!IMPORTANT]
> This plan involves upgrading the Kotlin version from `2.1.20` to `2.4.10` (the current stable release as of July 2026). This upgrade is necessary to maintain compatibility with the latest versions of Ktorfit and Compose Multiplatform.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///home/evangelos-angelousis/Desktop/Projects/Food Nutrition APP/FoodNutrition/gradle/libs.versions.toml)
- Update `kotlin` version to `2.4.10`.
- Update `ksp` version to `2.3.10` (compatible with Kotlin 2.4.10).
- Update `ktorfit` version to `2.7.5` (latest stable).

## Verification Plan

### Automated Tests
- Run `./gradlew :composeApp:compileDebugKotlinAndroid` to ensure the compilation error is resolved.
- Run `./gradlew build` to verify the entire project builds successfully.

### Manual Verification
- Deploy the app to an Android device/emulator to ensure runtime functionality is preserved.
