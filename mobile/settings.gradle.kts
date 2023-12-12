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
    ":core:data:surface",
    ":core:data:interior",
    ":core:domain:base",
    ":core:domain:service",
    ":core:ui",
)
include(":app")
include(
    ":feature:news",
    ":feature:post",
    ":feature:profile",
)
