object Dependencies {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

    // Hilt
    const val Hilt = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_KAPT = "com.google.dagger:hilt-compiler:${Versions.HILT}"

    // Coroutine
    const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE}"

    // Lifecycle
    const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"

    // Navigation
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

    // CircleImageView
    const val CIRCLE_IMAGEVIEW = "de.hdodenhof:circleimageview:${Versions.CIRCLE_IMAGEVIEW}"

    // Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val OKHTTP3 = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP3}"
    const val OKHTTP3_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP3}"

    // Glide
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

    // Photo View
    const val PHOTOVIEW = "com.github.chrisbanes:PhotoView:${Versions.PHOTOVIEW}"
}