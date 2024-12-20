plugins {
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.android.library")
    id("signing")
    id("com.vanniktech.maven.publish")
}

android {

    compileSdk = 35
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
}

dependencies {

    // Commons Serial
    api("io.github.hellogoogle2000:kotlin-commons:1.0.19")

    // Android Adapters
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    api("io.reactivex.rxjava3:rxandroid:3.0.2")

    // Jetpack
    api("androidx.core:core-ktx:1.15.0")
    api("androidx.appcompat:appcompat:1.7.0")
    api("androidx.activity:activity:1.9.3")
    api("androidx.activity:activity-ktx:1.9.3")
    api("androidx.activity:activity-compose:1.9.3")
    api("androidx.fragment:fragment:1.8.5")
    api("androidx.fragment:fragment-ktx:1.8.5")
    api("androidx.fragment:fragment-compose:1.8.5")
    api("androidx.constraintlayout:constraintlayout:2.2.0")
    api("androidx.constraintlayout:constraintlayout-compose:1.1.0")
    api("androidx.recyclerview:recyclerview:1.3.2")
    api("androidx.lifecycle:lifecycle-runtime:2.8.7")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    api("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    api("androidx.lifecycle:lifecycle-viewmodel:2.8.7")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    api("androidx.navigation:navigation-fragment:2.8.4")
    api("androidx.navigation:navigation-fragment-ktx:2.8.4")
    api("androidx.navigation:navigation-fragment-compose:2.8.4")
    api("androidx.navigation:navigation-ui:2.8.4")
    api("androidx.navigation:navigation-ui-ktx:2.8.4")
    api("androidx.navigation:navigation-compose:2.8.4")
    api("androidx.viewpager2:viewpager2:1.1.0")
    api("androidx.paging:paging-runtime:3.3.4")
    api("androidx.paging:paging-compose:3.3.4")
    api("androidx.drawerlayout:drawerlayout:1.2.0")
    api("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    api("androidx.window:window:1.3.0")
    api("androidx.window:window-java:1.3.0")
    api("com.google.android.material:material:1.12.0")

    // Compose
    api("androidx.compose.foundation:foundation:1.7.5")
    api("androidx.compose.runtime:runtime:1.7.5")
    api("androidx.compose.ui:ui:1.7.5")
    api("androidx.compose.ui:ui-tooling:1.7.5")
    api("androidx.compose.ui:ui-tooling-preview:1.7.5")
    api("androidx.compose.material:material:1.7.5")
    api("androidx.compose.material3:material3:1.3.1")
    api("androidx.compose.material3.adaptive:adaptive-android:1.0.0")
    api("androidx.compose.animation:animation:1.7.5")

    // coil3
    api("io.coil-kt.coil3:coil-compose:3.0.3")
    api("io.coil-kt.coil3:coil-network-okhttp:3.0.3")

    // Third-Part
    api("com.blankj:utilcodex:1.31.1")
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