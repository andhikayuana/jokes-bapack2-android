plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt").version("1.21.0")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "id.yuana.jokesbapack2"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
        compose = true
    }
    namespace = "id.yuana.jokesbapack2"

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-rc02"
    }
}

detekt {
    toolVersion = "1.21.0"
    config = files("../config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.network)
    implementation(libs.hilt.android)
    implementation(libs.material)

    kapt(libs.bundles.compiler)

    testImplementation(libs.bundles.testing)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.4")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("junit:junit:4.+")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}