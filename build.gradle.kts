// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false

//    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false
//    id("com.google.dagger.hilt.android") version "2.44" apply false
//    id("org.jetbrains.kotlin.kapt") version "2.0.0-RC3" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false

}
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        //noinspection UseTomlInstead
        classpath ("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}
apply(plugin = "org.jetbrains.kotlin.kapt")
val buildToolsVersion by extra("34.0.0")
