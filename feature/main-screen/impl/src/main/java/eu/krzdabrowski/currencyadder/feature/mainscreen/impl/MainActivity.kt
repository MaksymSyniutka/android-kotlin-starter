package eu.krzdabrowski.currencyadder.feature.mainscreen.impl

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import eu.krzdabrowski.currencyadder.core.compose.theme.CurrencyAdderTheme
import eu.krzdabrowski.currencyadder.core.navigation.android.NavigationFactory
import eu.krzdabrowski.currencyadder.core.navigation.android.NavigationManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyAdderTheme {
                MainScreen(
                    navigationFactories = navigationFactories,
                    navigationManager = navigationManager
                )
            }
        }
    }
}
