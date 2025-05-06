
import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import java.io.File
import java.util.Properties

// Function to load .env file
fun envProperties (): Properties {
    val properties = Properties()
    val envFile = rootDir.resolve(".env")

    if (envFile.exists()) {
        properties.load(envFile.inputStream())
    } else {
        println(".env file not found")
    }
    return properties
}
//val apiToken = envProperties().getProperty("API_TOKEN", "default_value")


plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
    id("com.google.firebase.appdistribution")

}

android {
    namespace = "com.iti.uc3.mealplans"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.iti.uc3.mealplans"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
//        buildConfigField("String", "API_TOKEN", "\"$apiToken\"")

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            firebaseAppDistribution {
                appId = "1:777663286370:android:6c4af0099cc9fae638953b"
                artifactType = "APK"
//                releaseNotesFile = "path/to/releasenotes.txt"
                testers = "ahmeddly30971@gmail.com"


            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.annotation)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.google.android.material:material:1.11.0")



    // splashscreen
    implementation("androidx.core:core-splashscreen:1.0.0")

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))

    // When using the BoM, you don't specify versions in Firebase library dependencies

    // Add the dependency for the Firebase SDK for Google Analytics
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    implementation ("com.google.firebase:firebase-database:21.0.0")
    implementation ("com.google.firebase:firebase-storage:21.0.0")

    implementation ("com.facebook.android:facebook-android-sdk:latest.release")
    implementation ("com.facebook.android:facebook-login:latest.release")

    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")






}