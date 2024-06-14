import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinKapt)
    id("kotlin-android")
    id("com.google.dagger.hilt.android")
}

val localProperties = Properties()
val keystoreFile = project.rootProject.file("private.properties")
val properties = Properties()
properties.load(keystoreFile.inputStream())
val PRIVATE_KEY = properties.getProperty("PRIVATE_KEY") ?: ""

android {
    namespace = "com.marveluniverse.www"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.marveluniverse.www"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        defaultConfig{
            buildConfigField("String", "API_KEY", project.properties["PUBLIC_KEY"] as String)
            buildConfigField("String", "API_PRIVATE_KEY", PRIVATE_KEY)
            buildConfigField("String", "BASE_URL", project.properties["BASE_URL"] as String)
        }
        release {
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
        jvmTarget = "1.8"
    }
    dataBinding { enable = true }
    android.buildFeatures.buildConfig = true
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    //image loading
    implementation("com.squareup.picasso:picasso:2.8")

    //network call
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")

    //dependency injection
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    //local storage
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-paging:$room_version")

    //logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.gson)

    //testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}