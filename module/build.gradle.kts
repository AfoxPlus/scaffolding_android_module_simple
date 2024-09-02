plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.sonarqube") version "3.3"
    id("jacoco")
}

apply {
    from(Gradle.Sonarqube)
    from(Gradle.Jacoco)
    from(Gradle.UploadArtifact)
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
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
    //Jetpack
    implementation(Deps.Jetpack.kotlin)
    implementation(Deps.Jetpack.core)
    implementation(Deps.Jetpack.appcompat)
    implementation(Deps.Jetpack.fragment)

    //Jetpack UI
    implementation(Deps.UI.materialDesign)
    implementation(Deps.UI.constraintLayout)

    // Jetpack Compose
    implementation(Deps.JetpackCompose.activity)
    implementation(Deps.JetpackCompose.constraintlayout)
    implementation(Deps.JetpackCompose.navigation)
    implementation(platform(Deps.JetpackCompose.bom))
    implementation(Deps.JetpackCompose.ui)
    implementation(Deps.JetpackCompose.graphics)
    implementation(Deps.JetpackCompose.toolingPreview)
    debugImplementation(Deps.JetpackCompose.tooling)
    implementation(Deps.JetpackCompose.material3)
    implementation(Deps.JetpackCompose.materialIconExtended)
    //Image Async
    implementation(Deps.JetpackCompose.coilCompose)
    implementation(Deps.UI.glide)
    kapt(Deps.UI.glideCompiler)

    // Coroutines
    implementation(Deps.Arch.coroutinesCore)
    implementation(Deps.Arch.coroutinesAndroid)

    //Lifecycle Scope
    implementation(Deps.Arch.lifecycleRuntime)
    implementation(Deps.Arch.lifecycleViewModel)
    implementation(Deps.Arch.lifecycleCompose)
    implementation(Deps.Arch.lifecycleRuntimeCompose)

    // Dagger - Hilt
    implementation(Deps.Arch.hiltAndroid)
    kapt(Deps.Arch.hiltAndroidCompiler)
    implementation(Deps.JetpackCompose.hiltNavigationCompose)
    kapt(Deps.Arch.hiltCompiler)

    //Retrofit
    implementation(Deps.Arch.retrofit2)
    implementation(Deps.Arch.gson)
    implementation(Deps.Arch.loggingInterceptor)

    // Test
    testImplementation(Deps.Test.jUnit)
    testImplementation(Deps.Test.testCore)
    testImplementation(Deps.Test.truth)
    testImplementation(Deps.Test.mockitoKotlin)
    testImplementation(Deps.Test.kotlinCoroutine)
    testImplementation(Deps.Test.mockitoInline)
    androidTestImplementation(Deps.Test.androidJUnit)
    androidTestImplementation(Deps.Test.espresso)

    // Business Dependencies
    implementation(Deps.UI.uikit)
    implementation(Deps.Arch.network)
}