buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.gradlePluginz)
        classpath(libs.kotlin.gradlePluginz)
        classpath(libs.ksp.gradlePluginz)
        classpath(libs.androidx.navigation.navigationSafeArgsPluginz)
        classpath(libs.google.dagger.hiltGradlePluginz)
        classpath(libs.google.firebase.crashlyticsGradle)
        classpath(libs.google.ossLicensesPluginz)
        classpath(libs.google.services)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
