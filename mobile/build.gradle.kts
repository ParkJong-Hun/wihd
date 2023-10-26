// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidGradlePlugin) apply false
    alias(libs.plugins.androidGradleLibraryPlugin) apply false
    alias(libs.plugins.kotlinPlugin) apply false
    alias(libs.plugins.kspPlugin) apply false
}
true // Needed to make the Suppress annotation work for the plugins block