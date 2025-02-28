import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvmToolchain(17)
    jvm("desktop")

    sourceSets["desktopMain"].dependencies {
        implementation(project(":sample:common"))
        implementation(compose.desktop.currentOs)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "io.github.s4nchouz.spotlightonboarding.sample"
            packageVersion = "1.0.0"
        }
    }
}