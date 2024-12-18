package com.ramesh.assessment.navigation.navdrawer.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.ramesh.assessment.navigation.model.BottomBar
import com.ramesh.core.util.Dimens


@Composable
fun NavDrawerSection(
    modifier: Modifier = Modifier,
    navigationItemContentList: List<BottomBar>,
    navController: NavHostController,
    currentDestination: NavDestination?,
    onDrawerAction: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(Dimens.dp32),
        ) {
            Spacer(modifier = modifier.height(Dimens.dp16))
            NavDrawerHeader(modifier = modifier)
            Spacer(modifier = modifier.height(Dimens.dp16))
            Divider(
                thickness = Dimens.dp1,
                color = Color.White.copy(alpha = 0.5f),
            )
            Spacer(modifier = modifier.height(Dimens.dp16))
            NavDrawerMenu(
                navigationItemContentList = navigationItemContentList,
                navController = navController,
                currentDestination = currentDestination,
                onDrawerAction = onDrawerAction,
            )
        }
    }
}