package eu.krzdabrowski.currencyadder.basicfeature.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import eu.krzdabrowski.currencyadder.basicfeature.R
import eu.krzdabrowski.currencyadder.basicfeature.presentation.model.ExchangeRateDisplayable
import eu.krzdabrowski.currencyadder.core.ui.Typography

@Composable
fun RocketItem(
    rocket: ExchangeRateDisplayable,
    modifier: Modifier = Modifier,
    onRocketClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.dimen_medium)
            )
            .testTag(
                stringResource(R.string.rocket_content_description)
            )
            .clickable { onRocketClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dimen_small)
            )
        ) {
            Text(
                text = rocket.currencyCode,
                style = Typography.titleMedium
            )

            Text(
                text = rocket.currencyRate,
                style = Typography.bodyMedium
            )
        }
    }
}
