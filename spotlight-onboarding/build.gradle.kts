import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.publishPlugin)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    jvmToolchain(17)
    jvm("desktop")

    js(IR) {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            testTask {
                enabled = false
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets.commonMain.dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(libs.kotlinx.uuid)
    }
}

android {
    namespace = "io.github.s4nchouz.spotlightonboarding"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

mavenPublishing {
    coordinates(
        groupId = "io.github.s4nchouz",
        artifactId = "spotlight-onboarding",
        version = "1.1.1"
    )

    pom {
        name = "Spotlight Onboarding KMP"
        description = "Compose Multiplatform library for creating guided onboarding experiences with a spotlight effect"
        inceptionYear = "2025"
        url = "https://github.com/s4nchouz/SpotlightOnboarding"

        licenses {
            license {
                name = "Apache"
                url = "https://www.apache.org/licenses/LICENSE-2.0"
            }
        }

        developers {
            developer {
                id = "s4nchouz"
                name = "Aleksandr Anokhin"
                email = "s4nchouz@icloud.com"
            }
        }

        scm {
            url = "https://github.com/s4nchouz/SpotlightOnboarding"
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()
}
