buildscript {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(Libs.androidGradlePlugin)
        classpath(Libs.kotlinPlugin)
        classpath(Libs.AndroidX.Navigation.navigationSafeArgsPlugin)
        classpath(Libs.Google.ossLicensesPlugin)
        classpath(Libs.Google.services)
        classpath(Libs.Google.Dagger.hiltPlugin)
        classpath(Libs.Google.Firebase.crashlyticsPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}