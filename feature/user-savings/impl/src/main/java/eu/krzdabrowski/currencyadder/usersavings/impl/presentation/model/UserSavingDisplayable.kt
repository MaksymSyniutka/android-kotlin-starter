package eu.krzdabrowski.currencyadder.usersavings.impl.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserSavingDisplayable(
    val id: Long? = null,
    val timestamp: Long = -1L,
    val place: String,
    val amount: String,
    val currency: String,
    val currencyPossibilities: List<String>,
) : Parcelable
