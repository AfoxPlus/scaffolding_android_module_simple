plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("maven-publish")
}

apply(from = "sonarqube.gradle")
apply(from = "jacoco.gradle")

android {
    compileSdk = VersionApp.compileSdkVersion
    buildToolsVersion = VersionApp.buildToolsVersion

    defaultConfig {
        minSdk = VersionApp.minSdkVersion
        targetSdk = VersionApp.targetSdkVersion
        testInstrumentationRunner = VersionApp.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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

    buildFeatures { viewBinding = true }

    lint {
        isCheckDependencies = true
    }
}

dependencies {
    implementation(fileTree("libs") { include(listOf("*.jar", "*.aar")) })
    implementation(MainApplicationDependencies.kotlinStdlib)
    implementation(MainApplicationDependencies.coreKtx)
    implementation(MainApplicationDependencies.appCompat)
    implementation(MainApplicationDependencies.material)
    implementation(MainApplicationDependencies.constraintLayout)

    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.extJUnit)
    androidTestImplementation(TestDependencies.espressoCore)
}