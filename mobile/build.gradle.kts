// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidGradlePlugin) apply false
    alias(libs.plugins.androidGradleLibraryPlugin) apply false
    alias(libs.plugins.kotlinPlugin) apply false
    alias(libs.plugins.com.android.dynamic.feature) apply false
}
true // Needed to make the Suppress annotation work for the plugins block