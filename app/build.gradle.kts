plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
    id(Plugins.HILT)
}

android {
    namespace = "com.jymun.harusekki"
    compileSdk = DefaultConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.jymun.harusekki"
        minSdk = DefaultConfig.MIN_SDK_VERSION
        targetSdk = DefaultConfig.TARGET_SDK_VERSION
        versionCode = DefaultConfig.VERSION_CODE
        versionName = DefaultConfig.VERSION_NAME

        testInstrumentationRunner = DefaultConfig.TEST_INSTRUMENTATION_RUNNER
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
        dataBinding = true
    }
}

dependencies {

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)

    // Testing
    testImplementation(Testing.JUNIT4)
    androidTestImplementation(Testing.ANDROID_JUNIT)
    androidTestImplementation(Testing.ESPRESSO_CORE)

    // Hilt
    implementation(Dependencies.Hilt)
    kapt(Dependencies.HILT_KAPT)
}

kapt {
    correctErrorTypes = true
}