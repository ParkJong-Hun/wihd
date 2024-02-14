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
include(":app")
include(":app-components-catalog")
include(
    ":core:data:dao",
    ":core:data:interior",
    ":core:domain:base",
    ":core:domain:service",
    ":core:ui:system",
    ":core:ui:component",
)
include(
    ":feature:login",
    ":feature:top",
    ":feature:news",
    ":feature:post",
    ":feature:profile",
)
include(":test")
