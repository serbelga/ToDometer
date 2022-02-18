plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.android.gms.oss-licenses-plugin")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = 31
    buildToolsVersion = "31.0.0"

    defaultConfig {
        applicationId = "com.sergiobelda.androidtodometer"
        minSdk = 24
        targetSdk = 31
        versionCode = 22
        versionName = "1.4.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            firebaseCrashlytics {
                mappingFileUploadEnabled = false
            }
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

val ktlint: Configuration by configurations.creating

dependencies {

    ktlint(libs.ktlint)

    implementation(fileTree("libs") { include(listOf("*.jar")) })

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.coreKtx)
    implementation(libs.androidx.core.coreSplashScreen)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.fragment.fragmentKtx)
    implementation(libs.androidx.lifecycle.livedataKtx)
    implementation(libs.androidx.lifecycle.runtimeKtx)
    implementation(libs.androidx.lifecycle.viewmodelKtx)
    implementation(libs.androidx.navigation.navigationFragmentKtx)
    implementation(libs.androidx.navigation.navigationUiKtx)
    implementation(libs.androidx.paging.pagingRuntimeKtx)
    implementation(libs.androidx.room.roomKtx)
    implementation(libs.androidx.room.roomRuntime)
    ksp(libs.androidx.room.roomCompiler)
    implementation(libs.google.dagger.hiltAndroid)
    implementation(platform(libs.google.firebase.firebaseBom))
    implementation(libs.google.firebase.firebaseAnalyticsKtx)
    implementation(libs.google.firebase.firebaseCrashlyticsKtx)
    kapt(libs.google.dagger.hiltAndroidCompiler)
    implementation(libs.google.material)
    implementation(libs.google.playServicesOssLicenses)
    implementation(libs.timber)
    implementation(libs.companion)

    testImplementation(libs.androidx.test.coreKtx)
    testImplementation(libs.androidx.test.ext.junitKtx)
    testImplementation(libs.google.dagger.hiltAndroidTesting)
    kaptTest(libs.google.dagger.hiltAndroidCompiler)
    testImplementation(libs.junit)
    testImplementation(libs.mockk.mockk)
    testImplementation(libs.robolectric.robolectric)

    androidTestImplementation(libs.androidx.arch.coreTesting)
    androidTestImplementation(libs.androidx.room.roomTesting)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.espressoCore)
    androidTestImplementation(libs.google.dagger.hiltAndroidTesting)
    kaptAndroidTest(libs.google.dagger.hiltAndroidCompiler)
}

task("ktlint", JavaExec::class) {
    group = "verification"
    main = "com.pinterest.ktlint.Main"
    classpath = configurations.getByName("ktlint")
    args = listOf("src/**/*.kt")
}

val check by tasks
check.dependsOn(tasks.getByName("ktlint"))

task("ktlintFormat", JavaExec::class) {
    group = "formatting"
    main = "com.pinterest.ktlint.Main"
    classpath = configurations.getByName("ktlint")
    args = listOf("-F", "src/**/*.kt")
}
