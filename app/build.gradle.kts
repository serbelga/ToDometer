plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.android.gms.oss-licenses-plugin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.sergiobelda.androidtodometer"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 4
        versionName = "1.0.0-beta01"

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
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

val ktlint by configurations.creating

dependencies {

    implementation(fileTree("libs") { include(listOf("*.jar")) })
    implementation(Libs.kotlin)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.legacy)
    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    implementation(Libs.AndroidX.Fragment.fragmentKtx)
    implementation(Libs.AndroidX.Navigation.navigationFragmentKtx)
    implementation(Libs.AndroidX.Navigation.navigationUiKtx)
    implementation(Libs.Google.Material.materialComponents)

    // Lifecycle
    implementation(Libs.AndroidX.Lifecycle.liveData)
    implementation(Libs.AndroidX.Lifecycle.viewModel)

    // Room dependencies
    implementation(Libs.AndroidX.Room.roomKtx)
    implementation(Libs.AndroidX.Room.roomRuntime)
    // Required: Room compiler (avoid RuntimeException - cannot find implementation for database)
    kapt(Libs.AndroidX.Room.roomCompiler)
    androidTestImplementation(Libs.AndroidX.Room.roomTesting)

    implementation(Libs.AndroidX.pagingRuntimeKtx)

    implementation(Libs.timber)

    implementation("com.google.android.gms:play-services-oss-licenses:17.0.0")

    implementation("com.github.serbelga:android-companion:1.0.0-alpha01")

    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")

    // AndroidX Test - JVM testing
    testImplementation(Libs.AndroidX.junitKtx)
    testImplementation(Libs.AndroidX.testCoreKtx)

    testImplementation(Libs.robolectric)

    implementation(Libs.Google.Dagger.hilt)
    kapt(Libs.Google.Dagger.hiltCompiler)

    implementation(Libs.AndroidX.Dagger.hiltLifecycleViewModel)
    // When using Kotlin.
    kapt(Libs.AndroidX.Dagger.hiltCompiler)

    // For instrumentation tests
    androidTestImplementation(Libs.Google.Dagger.hiltTesting)
    kaptAndroidTest(Libs.Google.Dagger.hiltCompiler)

    // For local unit tests
    testImplementation(Libs.Google.Dagger.hiltTesting)
    kaptTest(Libs.Google.Dagger.hiltCompiler)

    ktlint("com.pinterest:ktlint:0.40.0")
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
