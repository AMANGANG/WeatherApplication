plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.fragmentweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fragmentweather"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //TODO some of the dependencies are using a versions.toml file and another one is directly imported
    // Please fix this
    implementation(libs.lottie)
    implementation(libs.gsonConverter)
    implementation (libs.retrofit)

}