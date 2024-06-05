package es.ajrpachon.android.dependencies


class Dependencies {

    object Androidx {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.Androidx.appcompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Androidx.constraintLayout}"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.Androidx.lifecycleExtension}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Androidx.lifecycle}"
        const val lifecycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.Androidx.lifecycle}"
        const val liveDataKtxExtensions = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Androidx.lifecycle}"
        const val material = "material:material:${Versions.Androidx.material}"
        const val navigation = "androidx.navigation:navigation-ui:${Versions.Androidx.navigation}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.Androidx.navigation}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.Androidx.recyclerView}"
        const val room = "androidx.room:room-runtime:${Versions.Androidx.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.Androidx.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.Androidx.room}"
    }

    object Logging {
        const val timber = "com.jakewharton.timber:timber:${Versions.Logger.timber}"
        const val slf4j = "org.slf4j:slf4j-simple:${Versions.Logger.slf4j}"
        const val slf4jTimber = "at.favre.lib:slf4j-timber:${Versions.Logger.slf4Timber}"
        const val slf4jNop = "at.favre.lib:slf4j-nop:${Versions.Logger.slf4j}"
    }

    object Dagger {
        const val core = "com.google.dagger:dagger:${Versions.Dagger.hilt}"
        const val coreCompiler = "com.google.dagger:dagger-compiler:${Versions.Dagger.hilt}"
        const val androidCompiler = "com.google.dagger:dagger-android-processor:${Versions.Dagger.hilt}"
        const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.Dagger.hilt}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.Dagger.hilt}"
        const val hiltCore = "com.google.dagger:hilt-core:${Versions.Dagger.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Dagger.hilt}"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-fragment:${Versions.Dagger.hiltNavigation}"
    }

    object Kotlin {
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
        const val ktxCore = "androidx.core:core-ktx:${Versions.Kotlin.ktx}"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlin}"
        const val stdlibjdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin.kotlin}"
    }

    object OkHttp {
        const val core = "com.squareup.okhttp3:okhttp:${Versions.Http.okhttp}"
        const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Http.okhttp}"
        const val httpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.Http.okhttp}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.Http.retrofit}"
        const val moshi = "com.squareup.moshi:moshi:${Versions.Http.moshi}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.Http.moshi}"
        const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.Http.moshi}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.Http.retrofit}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.Glide.glideVersion}"
        const val glideAnnotation = "com.github.bumptech.glide:annotations:${Versions.Glide.glideVersion}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.Glide.glideVersion}"
    }
}