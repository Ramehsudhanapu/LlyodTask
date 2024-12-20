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
import com.ramesh.assessment.categories.CategoryListScreen
import com.ramesh.assessment.detail.DetailScreen
import com.ramesh.assessment.home.HomeScreen
import com.ramesh.assessment.navigation.model.BottomBarScreen
import com.ramesh.assessment.navigation.model.GeneralScreen

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
            CategoryListScreen(
                navController = navController,
                navigateToHome = { categoryName ->
                    navController.navigate(GeneralScreen.HomeScreen.createRoute(categoryName))
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
            route = GeneralScreen.HomeScreen.route,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) {
            val categoryName = it.arguments?.getString("categoryName") ?: ""
            HomeScreen(
                categoryName = categoryName,
                navController = navController,
                navigateToDetail = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                }
            )
        }
        composable(
            route = GeneralScreen.DetailProduct.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("productId") ?: -1
            DetailScreen(
                productId = id,
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}