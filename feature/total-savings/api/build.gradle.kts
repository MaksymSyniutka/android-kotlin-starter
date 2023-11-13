plugins {
    id(libs.plugins.common.kotlin.library.module)
    id("java-test-fixtures")
}

dependencies {
    implementation(libs.kotlin.coroutines)
}