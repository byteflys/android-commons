plugins {
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.android.library")
    id("signing")
    id("com.vanniktech.maven.publish")
}

android {

    compileSdk = 34
    defaultConfig {
        namespace = "x.android.commons"
        minSdk = 30
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {

    // Commons Serial
    api("io.github.hellogoogle2000:kotlin-commons:1.0.18")

    // Android Adapters
    api("androidx.core:core-ktx:1.15.0")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    api("io.reactivex.rxjava3:rxandroid:3.0.2")

    // AndroidX
    api("androidx.appcompat:appcompat:1.7.0")
    api("androidx.activity:activity:1.9.3")
    api("androidx.fragment:fragment-ktx:1.8.2")
    api("androidx.constraintlayout:constraintlayout:2.1.4")
    api("com.google.android.material:material:1.12.0")

    // Navigation
    api("androidx.navigation:navigation-ui-ktx:2.8.0")
    api("androidx.navigation:navigation-fragment-ktx:2.8.0")

    // Lifecycle
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

    // Compose
    api("androidx.compose.ui:ui:1.7.5")
    api("androidx.compose.ui:ui-graphics:1.7.5")
    api("androidx.compose.ui:ui-tooling:1.7.5")
    api("androidx.compose.ui:ui-tooling-preview:1.7.5")
    api("androidx.compose.ui:ui-tooling-preview-android:1.7.5")
    api("androidx.compose.material3:material3:1.3.0")
    api("androidx.activity:activity-compose:1.9.2")
    api("io.coil-kt.coil3:coil-compose:3.0.2")

    // Third-Part
    api("com.blankj:utilcodex:1.31.0")
}

signing {
    useGpgCmd()
}

mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
    signAllPublications()
}

// gradle publishMavenPublicationToMavenLocal
// gradle publishMavenPublicationToMavenCentralRepository