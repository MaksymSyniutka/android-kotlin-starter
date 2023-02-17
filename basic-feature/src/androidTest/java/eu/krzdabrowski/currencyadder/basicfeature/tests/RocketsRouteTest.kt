package eu.krzdabrowski.currencyadder.basicfeature.tests

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import eu.krzdabrowski.currencyadder.basicfeature.data.generateTestRocketsFromDomain
import eu.krzdabrowski.currencyadder.basicfeature.presentation.composable.RocketsRoute
import eu.krzdabrowski.currencyadder.core.MainActivity
import eu.krzdabrowski.currencyadder.core.utils.getHiltTestViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class RocketsRouteTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val testRockets = generateTestRocketsFromDomain()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.activity.setContent {
            RocketsRoute(
                viewModel = composeTestRule.getHiltTestViewModel()
            )
        }
    }

    @Test
    fun rocketsRoute_whenHappyPath_shouldShowAllFakeRockets() {
        testRockets.forEach { rocket ->
            composeTestRule
                .onNodeWithText(rocket.currencyCode)
                .assertExists()
        }
    }
}
