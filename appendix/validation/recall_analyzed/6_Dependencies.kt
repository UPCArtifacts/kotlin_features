@file:Suppress("unused")

package nerd.tuxmobil.fahrplan.congress

object Android { //#singleton
    const val buildToolsVersion = "29.0.3" //#inference
    const val compileSdkVersion = 28 //#inference
    const val minSdkVersion = 14 //#inference
    const val targetSdkVersion = 28 //#inference
}

private const val kotlinVersion = "1.3.72" //#inference

object GradlePlugins { //#singleton

    private object Versions { //#singleton
        const val androidGradle = "3.6.3" //#inference
        const val gradleVersions = "0.28.0" //#inference
        const val sonarQubeGradle = "2.8" //#inference
        const val unmockGradle = "0.7.6" //#inference
        const val androidJunitJacocoGradle = "0.16.0" //#inference
    }

    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}" //#inference,string_template
    const val gradleVersions = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersions}" //#inference,string_template
    const val sonarQubeGradle = "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${Versions.sonarQubeGradle}" //#inference,string_template
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion" //#inference,string_template
    const val unmockGradle = "de.mobilej.unmock:UnMockPlugin:${Versions.unmockGradle}" //#inference,string_template
    const val androidJunitJacocoGradle = "com.vanniktech:gradle-android-junit-jacoco-plugin:${Versions.androidJunitJacocoGradle}" //#inference,string_template
}

object Libs { //#singleton

    private object Versions { //#singleton
        const val assertjAndroid = "1.2.0" //#inference
        const val constraintLayout = "1.1.3" //#inference
        const val emailIntentBuilder = "2.0.0" //#inference
        const val engelsystem = "3.1.0" //#inference
        const val espresso = "3.0.2" //#inference
        const val junit = "4.13" //#inference
        const val kotlinCoroutines = "1.1.1" //#inference
        const val kotlinCoroutinesRetrofit = "1.1.0" //#inference
        const val mockito = "3.3.0" //#inference
        const val mockitoKotlin = "2.2.0" //#inference
        const val moshi = "1.9.2" //#inference
        const val okhttp = "3.12.12" //#inference
        const val robolectric = "4.3_r2-robolectric-0" //#inference
        const val snackengage = "0.22" //#inference
        const val supportLibrary = "28.0.0" //#inference
        const val testRules = "1.0.2" //#inference
        const val threeTenBp = "1.4.4" //#inference
        const val tracedroid = "1.4" //#inference
        const val truth = "1.0.1" //#inference
    }

    const val assertjAndroid = "com.squareup.assertj:assertj-android:${Versions.assertjAndroid}" //#inference,string_template
    const val emailIntentBuilder = "de.cketti.mailto:email-intent-builder:${Versions.emailIntentBuilder}" //#inference,string_template
    const val engelsystem = "info.metadude.kotlin.library.engelsystem:engelsystem-base:${Versions.engelsystem}" //#inference,string_template
    const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}" //#inference,string_template
    const val junit = "junit:junit:${Versions.junit}" //#inference,string_template
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}" //#inference,string_template
    const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}" //#inference,string_template
    const val kotlinCoroutinesRetrofit = "ru.gildor.coroutines:kotlin-coroutines-retrofit:${Versions.kotlinCoroutinesRetrofit}" //#inference,string_template
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion" //#inference,string_template
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}" //#inference,string_template
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}" //#inference,string_template
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}" //#inference,string_template
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}" //#inference,string_template
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}" //#inference,string_template
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}" //#inference,string_template
    const val robolectric = "org.robolectric:android-all:${Versions.robolectric}" //#inference,string_template
    const val snackengagePlayrate = "com.github.ligi.snackengage:snackengage-playrate:${Versions.snackengage}" //#inference,string_template
    const val supportLibraryAnnotations = "com.android.support:support-annotations:${Versions.supportLibrary}" //#inference,string_template
    const val supportLibraryAppcompatV7 = "com.android.support:appcompat-v7:${Versions.supportLibrary}" //#inference,string_template
    const val supportLibraryConstraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}" //#inference,string_template
    const val supportLibraryDesign = "com.android.support:design:${Versions.supportLibrary}" //#inference,string_template
    const val testRules = "com.android.support.test:rules:${Versions.testRules}" //#inference,string_template
    const val threeTenBp = "org.threeten:threetenbp:${Versions.threeTenBp}" //#inference,string_template
    const val tracedroid = "org.ligi:tracedroid:${Versions.tracedroid}" //#inference,string_template
    const val truth = "com.google.truth:truth:${Versions.truth}" //#inference,string_template
}
