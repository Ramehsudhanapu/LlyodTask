package com.ramesh.assessment.navigation.model


sealed class GeneralScreen(val route: String) {
    object HomeScreen : GeneralScreen("home/{categoryName}") {
        fun createRoute(categoryName: String) = "home/$categoryName"
    }

    object DetailProduct : GeneralScreen("detail/{productId}") {
        fun createRoute(productId: Int) = "detail/$productId"
    }
}