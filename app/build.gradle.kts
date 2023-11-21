plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

}

android {
    namespace = "com.example.lab2_sem5"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lab2_sem5"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies{

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.room:room-runtime:2.6.0")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.33.2-alpha")
    implementation("androidx.compose.ui:ui-text-android:1.6.0-alpha06")
    implementation ("androidx.compose.material:material-icons-extended")
    implementation ("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation ("androidx.compose.compiler:compiler:1.5.3")
    implementation("androidx.compose.foundation:foundation:1.0.4")
    implementation("androidx.compose.ui:ui:1.0.4")
    implementation("androidx.compose.ui:ui-tooling:1.0.4")
    implementation("androidx.compose.ui:ui-text:1.0.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}