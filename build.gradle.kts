// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // Integracion al proyecto deo uso de KSP como herramienta de desarrollo
    alias(libs.plugins.kotlin.ksp) apply false

    /// Si algo falla, cambiar este primero por favor
    // Para integrar Hilt en el proyecto
    id("com.google.dagger.hilt.android") version "2.56" apply false
    // Para usar Parcelize
    alias(libs.plugins.kotlin.parcelize) apply false
}

android {
    namespace = "uacj.mx.app08_appra"
    compileSdk = 35

    defaultConfig {
        applicationId = "uacj.mx.app08_appra"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
}

dependencies {

    // Para integrar Dagger Hilt y sus herramientas necesarias.
    implementation(libs.hilt.android)
    implementation(libs.hilt.viewmodel)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.foundation.layout.android)
    ksp(libs.hilt.android.compilador)

    val androidxhiltCompiler = "1.3.0" // Cambioar a 1.2.0
    implementation("androidx.hilt:hilt-work:${androidxhiltCompiler}")
    ksp("androidx.hilt:hilt-compiler:${androidxhiltCompiler}") //Esta dependencia nos permite

    // Dependencia para Hilt y evitar errores extraños
    implementation("androidx.work:work-runtime-ktx:2.7.0")

    // Para instalar Retrofit
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.gson.converter)
    implementation(libs.login.interceptor)

    //Implementa las librerias de Kotlinx para parcelizar (o utilizar JSON)
    implementation(libs.kotlinx.serialization.json)

    // Implementa las librerias de Navigation Compose
    implementation(libs.androidx.navigation.compose.android)

    // Librerias necesarias para decir y gestionar el tema de permisos y del uso de GPS:
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha")

    // Implementacion de Iconos
    implementation("androidx.compose.material:material-icons-extended-android:1.7.8")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
