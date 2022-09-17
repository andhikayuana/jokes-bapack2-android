// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


//ext {
//    nav_version = "2.3.5"
//    room_version = "2.4.0-alpha03"
//    retrofit_version = "2.9.0"
//    okhttp_version = "4.9.2"
//    hilt_version = "2.38.1"
//    ktx_version = "1.7.0"
//    appcompat_version = "1.3.1"
//    material_version = "1.4.0"
//    constraintlayout_version = "2.1.1"
//    lifecycle_version = "2.4.0"
//    arch_version = "2.1.0"
//    recyclerview_version = "1.2.1"
//
//
//}