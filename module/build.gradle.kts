plugins {
    id("com.android.library") version "8.0.2"
    id("org.jetbrains.kotlin.android") version "1.8.22"
    id("org.jetbrains.kotlin.kapt") version "1.8.22"
    id("com.google.dagger.hilt.android") version "2.44.2"
    id("org.jetbrains.kotlin.plugin.parcelize") version "1.7.20"
    id("org.sonarqube") version "3.3"
    id("jacoco")
}

apply {
    from("sonarqube.gradle")
    from("jacoco.gradle")
    from("upload.gradle")
    from("graph.gradle.kts")
}

android {
    namespace = "com.afoxplus.module"
    compileSdk = Versions.compileSdkVersion

    defaultConfig {
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        testInstrumentationRunner = Versions.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
        renderscriptSupportModeEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create("staging") {
            initWith(getByName("debug"))
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }

    lint {
        disable.addAll(
            listOf(
                "TypographyFractions",
                "TypographyQuotes",
                "JvmStaticProvidesInObjectDetector",
                "FieldSiteTargetOnQualifierAnnotation",
                "ModuleCompanionObjects",
                "ModuleCompanionObjectsNotInModuleParent"
            )
        )
        checkDependencies = true
        abortOnError = false
        ignoreWarnings = false
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(fileTree("libs") { include(listOf("*.jar", "*.aar")) })
    implementation(Deps.Jetpack.kotlin)
    implementation(Deps.Jetpack.core)
    implementation(Deps.Jetpack.appcompat)
    implementation(Deps.Jetpack.fragment)
    implementation(Deps.UI.materialDesign)
    implementation(Deps.UI.constraintLayout)

    // JetpackCompose
    implementation(Deps.JetpackCompose.activity)
    implementation(Deps.JetpackCompose.constraintlayout)
    implementation(Deps.JetpackCompose.navigation)
    implementation(platform(Deps.JetpackCompose.bom))
    implementation(Deps.JetpackCompose.ui)
    implementation(Deps.JetpackCompose.graphics)
    implementation(Deps.JetpackCompose.toolingPreview)
    implementation(Deps.JetpackCompose.material3)
    implementation(Deps.JetpackCompose.materialIconExtended)

    // External Libraries
    implementation(Deps.Arch.hiltAndroid)
    kapt(Deps.Arch.hiltCompiler)
    implementation(Deps.Arch.coroutinesCore)

    // Test
    testImplementation(Deps.Test.jUnit)
    testImplementation(Deps.Test.testCore)
    testImplementation(Deps.Test.truth)
    testImplementation(Deps.Test.mockitoKotlin)
    androidTestImplementation(Deps.Test.androidJUnit)
    androidTestImplementation(Deps.Test.espresso)

    // Business Dependencies
    implementation(Deps.UI.uikit)
    implementation(Deps.Arch.network)
}