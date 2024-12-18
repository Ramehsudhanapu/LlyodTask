import dependencies.LyodsDependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.ramesh.lloydscleanarchitecture"
    compileSdk = Versions.compile_sdk

    defaultConfig {
        applicationId = "com.ramesh.lloydscleanarchitecture"
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        versionCode = Versions.version_code
        versionName = Versions.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
            excludes += "META-INF/"
            excludes += "okhttp3/"
            excludes += "kotlin/"
            excludes += "org/"
            excludes += ".properties"
            excludes += ".bin"
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
}

dependencies {



    implementation(project(Modules.core))
    implementation(project(Modules.assessment))


    // TESTING
    testImplementation(LyodsDependencies.junit)
    androidTestImplementation(LyodsDependencies.test_ext_junit)
    androidTestImplementation(LyodsDependencies.espresso_core)
    androidTestImplementation(LyodsDependencies.junit_compose)
    debugImplementation(LyodsDependencies.ui_tooling)
    debugImplementation(LyodsDependencies.ui_test_manifest)

    // Hilt
    implementation(LyodsDependencies.hilt_android)
    kapt(LyodsDependencies.hilt_android_compiler)
    kapt(LyodsDependencies.hilt_compose_compiler)


    }



