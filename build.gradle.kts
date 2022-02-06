buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.androidGradlePlugin)
        classpath(Libs.kotlinPlugin)
        classpath(Libs.kspPlugin)
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
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
