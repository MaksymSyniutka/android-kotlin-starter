package eu.krzdabrowski.currencyadder.usersavings.ui

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import eu.krzdabrowski.currencyadder.usersavings.ui.model.UserSavingDisplayable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class UserSavingsUiState(
    val isLoading: Boolean = false,
    val userSavings: List<UserSavingDisplayable> = emptyList(),
    val isError: Boolean = false,
) : Parcelable {

    sealed interface PartialState {
        sealed interface UserSavingsPartialState : PartialState {
            data object Loading :
                UserSavingsPartialState // for simplicity: initial loading & refreshing
            data class UserSavingsWithCurrencyCodesFetched(
                val userSavings: List<UserSavingDisplayable>,
                val currencyCodes: List<String>,
            ) : UserSavingsPartialState
            data class Error(val throwable: Throwable) : UserSavingsPartialState
        }

        data class CurrencyCodesFiltered(
            val filteredCurrencyCodes: List<String>,
            val userSavingId: Long,
        ) : PartialState
    }
}
