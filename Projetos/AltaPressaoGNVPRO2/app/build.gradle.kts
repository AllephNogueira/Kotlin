plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)

}

android {
    namespace = "com.allephnogueira.altapressaognvpro"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.allephnogueira.altapressaognvpro"
        minSdk = 24
        targetSdk = 34
        versionCode = 4
        versionName = "4.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // BOM
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))

    // Analytics
    implementation("com.google.firebase:firebase-analytics")

    // Cloud Firestor
    implementation("com.google.firebase:firebase-firestore")

    // Firebase Auth
    implementation("com.google.firebase:firebase-auth")

    // Autenticacao google play
    implementation("com.google.android.gms:play-services-auth:21.0.0")

    // Dependencia para acessar localização do usuario

    implementation("com.google.android.gms:play-services-location:18.0.0")


}