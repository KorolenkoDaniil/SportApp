plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.sportapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.sportapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = null // Убираем подпись
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}


dependencies {


    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.jsonlib)


    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)


    implementation(libs.retrofit2.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.permissions)


    implementation(libs.firebase.analytics)
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.compose.ui)
    implementation(libs.compose.runtime)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.support.annotations)
    implementation(libs.firebase.messaging)
    implementation(libs.androidx.annotation)

    implementation(libs.androidx.material3)
    implementation(libs.androidx.foundation.layout.android)

    // Работа с изображениями (Coil)
    implementation(libs.coil.compose)

    // Ktor (HTTP-клиент)


    // JSON-сериализация
    implementation(libs.kotlinx.serialization.json)

    // Firebase
    implementation(libs.firebase.auth)
    implementation(libs.firebase.crashlytics.buildtools)

    // Видео и медиаконтент
    implementation(libs.android.youtube.player)
    implementation(libs.androidx.media3.common.ktx)

    // Google Fonts
    implementation(libs.googleFonts)
    implementation(libs.firebase.firestore.ktx)

    // Тестирование
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))

}

