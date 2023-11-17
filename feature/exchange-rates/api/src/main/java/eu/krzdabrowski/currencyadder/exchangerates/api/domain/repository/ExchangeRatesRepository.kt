package eu.krzdabrowski.currencyadder.exchangerates.api.domain.repository

import kotlinx.coroutines.flow.Flow

interface ExchangeRatesRepository {

    fun getAllCurrencyCodes(): Flow<Result<List<String>>>

    suspend fun getCurrencyCodesThatStartWith(searchPhrase: String): Result<List<String>>

    suspend fun refreshExchangeRates(): Result<Unit>
}
