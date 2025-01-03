package com.ramesh.assessment.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ramesh.core.R
import com.ramesh.core.ui.componets.particle.theme.LlyodTheme
import com.ramesh.core.util.Dimens
import kotlinx.coroutines.delay
@Composable
fun SplashScreen(
    onTimeout: () -> Unit, modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        delay(2500)
        onTimeout()
    }
    Box(
        modifier = modifier
            .fillMaxSize()

            .background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = modifier
                    .padding(Dimens.dp16)
                    .shadow(elevation = Dimens.dp1, shape = RoundedCornerShape(Dimens.dp12),)
                    .clip(shape = RoundedCornerShape(Dimens.dp12)),
                painter = painterResource(id = R.drawable.llyodsplash),
                contentDescription = stringResource(id = R.string.app_name),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = modifier.height(Dimens.dp16))
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    LlyodTheme {
        SplashScreen(onTimeout = {})
    }
}