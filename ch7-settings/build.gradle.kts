plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.ch7_settings"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ch7_settings"
        minSdk = 24
        targetSdk = 33
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // androidx 라이브러리 중 xxx 와 xxx-ktx는 각각 자바로 만든 것, 그걸 코틀린으로 만든 것.

    // 자바 라이브러리의 클래스 등도 얼마든지 코틀린 코드에서 활용이 가능하기는 하지만, 약간 신경 써야 하는 포인트가 있다.
    // 이 불편함을 없애기 위해서 코틀린 라이브러리를 제공하는 것.
    implementation("androidx.preference:preference-ktx:1.2.1")
}