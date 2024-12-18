import dependencies.LyodsDependencies

plugins {

    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")

}

android {
    namespace = "com.ramesh.core"
    compileSdk = Versions.compile_sdk

    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    tasks.withType().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-opt-in=androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi",
            )
        }
    }


    dependencies {
// DEFAULT DEPENDENCIES
        api(LyodsDependencies.core_ktx)
        api(LyodsDependencies.lifecycle_ktx)

        // COMPOSE
        api(LyodsDependencies.activity_compose)
        api(platform("androidx.compose:compose-bom:2023.06.00"))
        api("androidx.compose.ui:ui")
        api("androidx.compose.ui:ui-graphics")
        api("androidx.compose.ui:ui-tooling-preview")
        api("androidx.compose.material3:material3")
        api("androidx.compose.material3:material3-window-size-class")
        api(LyodsDependencies.navigation_compose)



        // TESTING
        testImplementation(LyodsDependencies.junit)
        androidTestImplementation(LyodsDependencies.test_ext_junit)
        androidTestImplementation(LyodsDependencies.espresso_core)
        androidTestImplementation(platform("androidx.compose:compose-bom:2023.06.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")

        // MOCKITO-KOTLIN
        testImplementation(LyodsDependencies.mockito_kotlin)

        // COROUTINES TEST
        testImplementation(LyodsDependencies.coroutines_test)

        // REMOTE
        api(LyodsDependencies.retrofit)
        api(LyodsDependencies.retrofit2_converter_gson)
        api(LyodsDependencies.retrofit2_adapter_rxjava2)
        api(LyodsDependencies.okhttp3)

        // COIL
        api(LyodsDependencies.coil)

        // Hilt
        implementation(LyodsDependencies.hilt_android)
        kapt(LyodsDependencies.hilt_android_compiler)
        api(LyodsDependencies.hilt_compose) {
            exclude("androidx.lifecycle", "lifecycle-viewmodel-ktx")
        }
        kapt(LyodsDependencies.hilt_compose_compiler)




        // System UI Controller
        api(LyodsDependencies.accompanist_systemuicontroller)


    }
}