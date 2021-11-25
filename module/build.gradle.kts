plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("maven-publish")
}

apply(from = "sonarqube.gradle")
apply(from = "jacoco.gradle")
apply(from = "upload.gradle")

android {
    compileSdk = Versions.compileSdkVersion
    buildToolsVersion = Versions.buildToolsVersion

    defaultConfig {
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        testInstrumentationRunner = Versions.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
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

    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    lint {
        isCheckDependencies = true
    }
}

dependencies {
    implementation(fileTree("libs") { include(listOf("*.jar", "*.aar")) })
    implementation(Deps.Jetpack.kotlin)
    implementation(Deps.Jetpack.core)
    implementation(Deps.Jetpack.appcompat)
    implementation(Deps.Jetpack.activity)
    implementation(Deps.Jetpack.fragment)

    implementation(Deps.UI.materialDesign)
    implementation(Deps.UI.constraintLayout)
    implementation(Deps.UI.materialDesign)
    implementation(Deps.UI.constraintLayout)
    implementation(Deps.UI.uikit)

    implementation(Deps.Arch.coroutinesCore)
    implementation(Deps.Arch.hiltAndroid)
    kapt(Deps.Arch.hiltCompiler)
    implementation(Deps.Arch.network)

    testImplementation(Deps.Test.jUnit)
    androidTestImplementation(Deps.Test.androidJUnit)
    androidTestImplementation(Deps.Test.espresso)
}