package com.ramesh.lloydscleanarchitecture.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramesh.assessment.navigation.BottomNav
import com.ramesh.assessment.navigation.model.BottomBarScreen
import com.ramesh.lloydscleanarchitecture.utility.LlyodNaviType


@Composable
fun LloydMainScreen(modifier: Modifier = Modifier,
                    navigationType: LlyodNaviType,
                    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navigationItemContentList = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Cart,
        BottomBarScreen.Profile
    )

    when (navigationType) {
        LlyodNaviType.BOTTOM_NAVIGATION -> {
            BottomNav(
                modifier = modifier,
                navigationItemContentList = navigationItemContentList,
                navController = navController,
                currentDestination = currentDestination,
            )
        }




    }








    }