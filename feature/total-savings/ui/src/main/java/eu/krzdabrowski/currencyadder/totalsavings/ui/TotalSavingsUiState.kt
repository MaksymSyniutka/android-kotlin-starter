package eu.krzdabrowski.currencyadder.totalsavings.ui

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class TotalSavingsUiState(
    val totalUserSavings: String = "",
    val currencyCodes: List<String> = emptyList(),
    val chosenCurrencyCode: String = "",
) : Parcelable {

    sealed interface PartialState {
        data class TotalUserSavingsFetched(val totalUserSavings: String) : PartialState

        data class CurrencyCodesFetched(val currencyCodes: List<String>) : PartialState

        data class ChosenCurrencyCodeChanged(val currencyCode: String) : PartialState

        data object Error : PartialState
    }
}