plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
    id(Plugins.HILT)
    id(Plugins.NAVIGATION_SAFE_ARGS)
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

    testImplementation(Testing.TRUTH)
    testImplementation(Testing.COROUTINE)

    // Hilt
    implementation(Dependencies.Hilt)
    kapt(Dependencies.HILT_KAPT)

    // Coroutine
    implementation(Dependencies.COROUTINE)

    // Lifecycle
    implementation(Dependencies.VIEWMODEL)
    implementation(Dependencies.LIVEDATA)
    implementation(Dependencies.ACTIVITY_KTX)
    implementation(Dependencies.FRAGMENT_KTX)

    // Navigation
    implementation(Dependencies.NAVIGATION_UI_KTX)
    implementation(Dependencies.NAVIGATION_FRAGMENT_KTX)

    // Circle ImageView
    implementation(Dependencies.CIRCLE_IMAGEVIEW)

    // Retrofit
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.GSON_CONVERTER)
    implementation(Dependencies.OKHTTP3)
    implementation(Dependencies.OKHTTP3_INTERCEPTOR)

    // Glide
    implementation(Dependencies.GLIDE)
    annotationProcessor(Dependencies.GLIDE_COMPILER)

    // Photo View
    implementation(Dependencies.PHOTOVIEW)

    // Room
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    kapt(Dependencies.ROOM_COMPILER)
}

kapt {
    correctErrorTypes = true
}