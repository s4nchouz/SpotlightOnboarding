rootProject.name = "SpotlightOnboarding"

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencyResolutionManagement {
        @Suppress("UnstableApiUsage")
        repositories {
            google()
            mavenCentral()
            mavenLocal()
            maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }
    }
}

include(
    ":spotlightOnboarding",
    ":sample:android",
    ":sample:web",
    ":sample:common"
)
