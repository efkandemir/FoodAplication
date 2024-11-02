    plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.efkan.shoppingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.efkan.shoppingapp"
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
    buildFeatures{
        viewBinding= true
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
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.3")
    //Uygulama tasarımında farklı ekran boyutları ve yoğunlukları için boyutları ölçeklendirmeye yarar.
    implementation("com.intuit.sdp:sdp-android:1.0.6")

    //Benzer şekilde, metin boyutlarını farklı ekran boyutlarına göre ölçeklendirmek için kullanılır.
    implementation("com.intuit.ssp:ssp-android:1.0.6")

    //Android uygulamalarında GIF formatındaki animasyonları görüntülemek için kullanılır
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.17")

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation("com.squareup.picasso:picasso:2.8")
    //videoModel mvvm
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")


}