object Versions {
    const val androidDaggerHilt = "1.0.0-alpha02"
    const val androidGradlePlugin = "7.0.0-alpha04"
    const val androidLegacy = "1.0.0"
    const val appCompat = "1.3.0-beta01"
    const val constraintLayout = "2.1.0-alpha2"
    const val daggerHilt = "2.28.3-alpha"
    const val fragmentKtx = "1.3.0-rc01"
    const val junitKtx = "1.1.3-alpha03"
    const val kotlin = "1.4.20"
    const val ktLint = "0.40.0"
    const val ktxVersion = "1.5.0-beta01"
    const val lifecycle = "2.3.0-rc01"
    const val materialComponents = "1.2.1"
    const val navigation = "2.3.2"
    const val ossLicensesPlugin = "0.10.2"
    const val pagingRuntimeKtx = "2.1.2"
    const val robolectric = "4.3.1"
    const val roomKtx = "2.3.0-alpha04"
    const val testCoreKtx = "1.3.1-alpha03"
    const val timber = "4.7.1"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val ktLint = "com.pinterest:ktlint:${Versions.ktLint}"

    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    object AndroidX {

        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.ktxVersion}"
        const val legacy = "androidx.legacy:legacy-support-v4:${Versions.androidLegacy}"
        const val pagingRuntimeKtx = "androidx.paging:paging-runtime-ktx:${Versions.pagingRuntimeKtx}"
        const val junitKtx = "androidx.test.ext:junit-ktx:${Versions.junitKtx}"
        const val testCoreKtx = "androidx.test:core-ktx:${Versions.testCoreKtx}"

        object Dagger {
            const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.androidDaggerHilt}"
            const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.androidDaggerHilt}"
        }

        object Fragment {
            const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        }

        object Lifecycle {
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        }

        object Navigation {
            const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            const val navigationSafeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
            const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        }

        object Room {
            const val roomKtx = "androidx.room:room-ktx:${Versions.roomKtx}"
            const val roomRuntime = "androidx.room:room-runtime:${Versions.roomKtx}"
            const val roomCompiler = "androidx.room:room-compiler:${Versions.roomKtx}"
            const val roomTesting = "androidx.room:room-testing:${Versions.roomKtx}"
        }
    }

    object Google {

        const val ossLicensesPlugin = "com.google.android.gms:oss-licenses-plugin:${Versions.ossLicensesPlugin}"

        object Dagger {
            const val hilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
            const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
            const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
            const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.daggerHilt}"
        }

        object Material {
            const val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
        }
    }
}