// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("com.android.library") version(libs.versions.androidGradlePlugin.get()) apply false
    id("org.jetbrains.kotlin.android") version(libs.versions.kotlin.get()) apply false
    alias(libs.plugins.hilt) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}