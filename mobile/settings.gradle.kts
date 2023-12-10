pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "What-is-He-doing-with-Android"
include(
    ":app",
    ":core:data",
    ":core:domain:service",
    ":core:domain:base",
    ":core:ui",
)
include(
    ":feature:news",
    ":feature:post",
    ":feature:profile",
)
