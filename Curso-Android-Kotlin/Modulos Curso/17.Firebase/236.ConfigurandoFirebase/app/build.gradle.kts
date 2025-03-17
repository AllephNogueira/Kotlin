plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("com.google.gms.google-services")

}

android {
    namespace = "com.allephnogueira.aulafirebase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.allephnogueira.aulafirebase"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Firabase Bom
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))

    // Analutics
    implementation("com.google.firebase:firebase-analytics")

    // Autenticação
    implementation("com.google.firebase:firebase-auth")

    // Cloud Firestore banco de dados
    implementation("com.google.firebase:firebase-firestore")

    //Cloud Storage armazenamento
    implementation("com.google.firebase:firebase-storage")

    // Picaso
    implementation ("com.squareup.picasso:picasso:2.8")


}