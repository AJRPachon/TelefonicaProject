package es.ajrpachon.android.dependencies

object Classpaths {

    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.Build.gradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlin}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Androidx.navigation}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Dagger.hilt}"
}