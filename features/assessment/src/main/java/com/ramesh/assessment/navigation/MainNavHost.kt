package com.ramesh.assessment.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ramesh.assessment.detail.DetailScreen
import com.ramesh.assessment.home.HomeScreen

import com.ramesh.assessment.navigation.model.BottomBarScreen
import com.ramesh.assessment.navigation.model.GeneralScreen
import com.ramesh.assessment.search.SearchScreen


@Composable
fun MainNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(
                navigateToDetail = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                },
                navigateToSearch = {
                    navController.navigate(GeneralScreen.SearchProduct.route)
                }
            )
        }
        composable(BottomBarScreen.Cart.route) {
            Toast.makeText(navController.context, "Coming Soon....", Toast.LENGTH_SHORT).show()
        }
        composable(BottomBarScreen.Profile.route) {
            Toast.makeText(navController.context, "Profile", Toast.LENGTH_SHORT).show()

        }
        composable(
            route = GeneralScreen.DetailProduct.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType }),
        ) {
            val id = it.arguments?.getInt("productId") ?: -1
            DetailScreen(
                productId = id,
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
        composable(
            route = GeneralScreen.SearchProduct.route,
        ) {
            SearchScreen(
                navigateToDetail = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                },
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}