package eu.krzdabrowski.currencyadder.usersavings.api.domain.model

data class UserSaving(
    val id: Long? = null,
    val timestamp: Long,
    val place: String,
    val amount: Double,
    val currency: String,
)
