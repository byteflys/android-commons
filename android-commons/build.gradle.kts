plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.library")
    id("signing")
    id("maven-publish")
    id("com.vanniktech.maven.publish")
}

android {

    compileSdk = 34
    defaultConfig {
        namespace = "io.github.hellogoogle2000.android.commons"
        minSdk = 30
        targetSdk = 34
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

    // Kotlin
    api("androidx.core:core-ktx:1.13.1")

    // Coroutine
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // AndroidX
    api("androidx.appcompat:appcompat:1.7.0")
    api("androidx.fragment:fragment-ktx:1.8.2")
    api("androidx.constraintlayout:constraintlayout:2.1.4")
    api("com.google.android.material:material:1.12.0")

    // Jetpack
    api("androidx.navigation:navigation-ui-ktx:2.8.0")
    api("androidx.navigation:navigation-fragment-ktx:2.8.0")

    // Lifecycle
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

    // Compose
    api("androidx.compose.ui:ui:1.7.1")
    api("androidx.compose.ui:ui-graphics:1.7.1")
    api("androidx.compose.ui:ui-tooling-preview:1.7.1")
    api("androidx.compose.material3:material3:1.3.0")
    api("androidx.activity:activity-compose:1.9.2")
    api(platform("androidx.compose:compose-bom:2024.09.01"))

    // GSON
    api("com.google.code.gson:gson:2.11.0")

    // RxJava3
    api("io.reactivex.rxjava3:rxjava:3.1.9")
    api("io.reactivex.rxjava3:rxandroid:3.0.2")
    api("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")

    // OkHttp3
    api("com.squareup.okhttp3:okhttp:+")
    api("com.squareup.okhttp3:logging-interceptor:+")

    // Retrofit2
    api("com.squareup.retrofit2:retrofit:+")
    api("com.squareup.retrofit2:converter-gson:+")
    api("com.squareup.retrofit2:adapter-rxjava2:+")
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