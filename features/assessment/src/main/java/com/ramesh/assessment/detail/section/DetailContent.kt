package com.ramesh.assessment.detail.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramesh.core.R
import com.ramesh.core.data.model.Product
import com.ramesh.core.ui.componets.particle.theme.Gray200
import com.ramesh.core.util.Dimens
import com.ramesh.core.util.Extensions.myToast

@Composable
fun DetailContent(
    product: Product
) {
    val context = LocalContext.current
    val strBuy = stringResource(R.string.buy)
    val strAddedCart = stringResource(R.string.added_to_cart)
    val strThanks = stringResource(R.string.thank_you_buy)
    var buyText by remember { mutableStateOf(strBuy) }
    var isAlreadyOnCart by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(color = Color.White),
    ) {
        item {
            ImageProductPager(product = product)
            Spacer(modifier = Modifier.size(Dimens.dp8))
            TitleProduct(product = product)
            Divider(color = Gray200, thickness = 10.dp)
            DescriptionProduct(product = product)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomStart
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {

            if (!isAlreadyOnCart) {
                Text(
                    text = stringResource(R.string.add_to_cart),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 20.dp)
                        .clickable {
                            context.myToast(strAddedCart)

                        },
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
            Text(
                text = buyText,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 20.dp)
                    .clickable {
                        context.myToast(strThanks)
                        buyText = strThanks
                    },
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }
}