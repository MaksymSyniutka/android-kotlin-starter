plugins {
    id(libs.plugins.common.android.library.module)
    alias(libs.plugins.junit)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "eu.krzdabrowski.currencyadder.totalsavings.impl"

    testFixtures {
        enable = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(projects.core.architecture.android)
    implementation(projects.core.database.api)
    implementation(projects.core.utils.android)
    implementation(projects.feature.totalSavings.api)

    implementation(projects.feature.exchangeRates.api)
    implementation(projects.feature.totalSavings.api)
    implementation(projects.feature.userSavings.api)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.kotlin.coroutines)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.timber)
    testImplementation(libs.bundles.common.test)
    androidTestImplementation(libs.bundles.common.android.test)
    androidTestImplementation(libs.datastore) // needed for Hilt tests
    debugImplementation(libs.debug.compose.manifest)

    testFixturesImplementation(projects.feature.totalSavings.api)

    testFixturesImplementation(libs.bundles.common.android.test)
    testFixturesImplementation(libs.hilt)

    kspTestFixtures(libs.hilt.compiler)
    kspTestFixtures(libs.test.android.hilt.compiler)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)

    kspAndroidTest(libs.test.android.hilt.compiler)

    detektPlugins(libs.detekt.compose.rules)

    androidTestImplementation(testFixtures(projects.feature.exchangeRates.api))
    androidTestImplementation(testFixtures(projects.feature.totalSavings.api))
    androidTestImplementation(projects.core.utils.test.android)
}