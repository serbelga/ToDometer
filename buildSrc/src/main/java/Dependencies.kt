object Versions {
    const val archCore = "2.1.0"
    const val androidGradlePlugin = "4.2.1"
    const val androidLegacy = "1.0.0"
    const val appCompat = "1.2.0"
    const val constraintLayout = "2.0.4"
    const val daggerHilt = "2.35"
    const val espressoCore = "3.3.0"
    const val extJunit = "1.1.2"
    const val firebaseBom = "27.1.0"
    const val firebaseCrashlyticsPlugin = "2.5.2"
    const val fragmentKtx = "1.3.3"
    const val googleServices = "4.3.5"
    const val junit = "4.13.1"
    const val junitKtx = "1.1.2"
    const val kotlin = "1.5.0"
    const val ktLint = "0.41.0"
    const val ktxVersion = "1.3.2"
    const val lifecycle = "2.3.1"
    const val materialComponents = "1.3.0"
    const val mockk = "1.10.6"
    const val navigation = "2.3.5"
    const val ossLicenses = "17.0.0"
    const val ossLicensesPlugin = "0.10.2"
    const val pagingRuntimeKtx = "3.0.0"
    const val dataStorePreferences = "1.0.0-beta01"
    const val robolectric = "4.3.1"
    const val roomKtx = "2.3.0"
    const val testCoreKtx = "1.3.0"
    const val timber = "4.7.1"
    const val companion = "0.1.3"
}

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val ktLint = "com.pinterest:ktlint:${Versions.ktLint}"

    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val junit = "junit:junit:${Versions.junit}"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    object AndroidX {

        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.ktxVersion}"
        const val legacy = "androidx.legacy:legacy-support-v4:${Versions.androidLegacy}"
        const val pagingRuntimeKtx =
            "androidx.paging:paging-runtime-ktx:${Versions.pagingRuntimeKtx}"
        const val junitKtx = "androidx.test.ext:junit-ktx:${Versions.junitKtx}"
        const val testCoreKtx = "androidx.test:core-ktx:${Versions.testCoreKtx}"

        object DataStore {
            const val preferences =
                "androidx.datastore:datastore-preferences:${Versions.dataStorePreferences}"
        }

        object Fragment {
            const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        }

        object Lifecycle {
            const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCore}"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        }

        object Navigation {
            const val navigationFragmentKtx =
                "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            const val navigationSafeArgsPlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
            const val navigationUiKtx =
                "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        }

        object Room {
            const val roomKtx = "androidx.room:room-ktx:${Versions.roomKtx}"
            const val roomRuntime = "androidx.room:room-runtime:${Versions.roomKtx}"
            const val roomCompiler = "androidx.room:room-compiler:${Versions.roomKtx}"
            const val roomTesting = "androidx.room:room-testing:${Versions.roomKtx}"
        }

        object Test {
            const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
            const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
        }
    }

    object Google {

        const val ossLicensesPlugin =
            "com.google.android.gms:oss-licenses-plugin:${Versions.ossLicensesPlugin}"

        const val ossLicenses =
            "com.google.android.gms:play-services-oss-licenses:${Versions.ossLicenses}"

        const val services = "com.google.gms:google-services:${Versions.googleServices}"

        object Dagger {
            const val hilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
            const val hiltCompiler =
                "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
            const val hiltPlugin =
                "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
            const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.daggerHilt}"
        }

        object Firebase {
            const val analytics = "com.google.firebase:firebase-analytics-ktx"
            const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
            const val crashlyticsPlugin =
                "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsPlugin}"
            const val bom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        }

        object Material {
            const val materialComponents =
                "com.google.android.material:material:${Versions.materialComponents}"
        }
    }

    const val sergiobeldaCompanion = "dev.sergiobelda.android.companion:companion:${Versions.companion}"
}
