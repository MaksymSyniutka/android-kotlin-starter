package eu.krzdabrowski.currencyadder.addcurrency.impl

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import eu.krzdabrowski.currencyadder.totalsavings.impl.presentation.TotalSavingsIntent
import eu.krzdabrowski.currencyadder.totalsavings.impl.presentation.TotalSavingsIntent.UpdateChosenCurrencyCodeForTotalSavings
import eu.krzdabrowski.currencyadder.totalsavings.impl.presentation.TotalSavingsUiState
import eu.krzdabrowski.currencyadder.totalsavings.impl.presentation.TotalSavingsViewModel
import eu.krzdabrowski.currencyadder.totalsavings.impl.presentation.composable.TotalSavingsContent
import eu.krzdabrowski.currencyadder.usersavings.impl.presentation.UserSavingsIntent
import eu.krzdabrowski.currencyadder.usersavings.impl.presentation.UserSavingsIntent.AddUserSaving
import eu.krzdabrowski.currencyadder.usersavings.impl.presentation.UserSavingsIntent.GetCurrencyCodesThatStartWith
import eu.krzdabrowski.currencyadder.usersavings.impl.presentation.UserSavingsIntent.RefreshExchangeRates
import eu.krzdabrowski.currencyadder.usersavings.impl.presentation.UserSavingsIntent.RemoveUserSaving
import eu.krzdabrowski.currencyadder.usersavings.impl.presentation.UserSavingsIntent.SwapUserSavings
import eu.krzdabrowski.currencyadder.usersavings.impl.presentation.UserSavingsIntent.UpdateUserSaving
import eu.krzdabrowski.currencyadder.usersavings.impl.presentation.UserSavingsUiState
import eu.krzdabrowski.currencyadder.usersavings.impl.presentation.UserSavingsViewModel
import eu.krzdabrowski.currencyadder.usersavings.impl.presentation.composable.UserSavingsContent

private const val USER_SAVINGS_SCREEN_HEIGHT_FRACTION = 0.8f

@Composable
internal fun CurrencyAdderRoute(
    userSavingsViewModel: UserSavingsViewModel = hiltViewModel(),
    totalSavingsViewModel: TotalSavingsViewModel = hiltViewModel(),
) {
    val userSavingsUiState by userSavingsViewModel.uiState.collectAsStateWithLifecycle()
    val totalSavingsUiState by totalSavingsViewModel.uiState.collectAsStateWithLifecycle()

    CurrencyAdderScreen(
        userSavingsUiState = userSavingsUiState,
        totalSavingsUiState = totalSavingsUiState,
        onUserSavingsIntent = userSavingsViewModel::acceptIntent,
        onTotalSavingsIntent = totalSavingsViewModel::acceptIntent,
    )
}

@Composable
private fun CurrencyAdderScreen(
    userSavingsUiState: UserSavingsUiState,
    totalSavingsUiState: TotalSavingsUiState,
    onUserSavingsIntent: (UserSavingsIntent) -> Unit,
    onTotalSavingsIntent: (TotalSavingsIntent) -> Unit,
) {
    Column {
        UserSavingsContent(
            uiState = userSavingsUiState,
            modifier = Modifier.fillMaxHeight(USER_SAVINGS_SCREEN_HEIGHT_FRACTION),
            onAddUserSaving = {
                onUserSavingsIntent(AddUserSaving)
            },
            onUpdateUserSaving = {
                onUserSavingsIntent(UpdateUserSaving(it))
            },
            onRemoveUserSaving = {
                onUserSavingsIntent(RemoveUserSaving(it))
            },
            onDragAndDropUserSaving = { fromListItemIndex, toListItemIndex ->
                onUserSavingsIntent(SwapUserSavings(fromListItemIndex, toListItemIndex))
            },
            getCurrencyCodesThatStartWith = { searchPhrase, itemId ->
                onUserSavingsIntent(GetCurrencyCodesThatStartWith(searchPhrase, itemId))
            },
            onRefreshExchangeRates = {
                onUserSavingsIntent(RefreshExchangeRates)
            },
        )

        TotalSavingsContent(
            uiState = totalSavingsUiState,
            onGetTotalUserSavingsInChosenCurrency = {
                onTotalSavingsIntent(UpdateChosenCurrencyCodeForTotalSavings(it))
            },
            modifier = Modifier.wrapContentHeight(),
        )
    }
}
