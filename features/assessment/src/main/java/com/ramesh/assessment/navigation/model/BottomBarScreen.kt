package com.ramesh.assessment.navigation.model



import com.ramesh.core.R


sealed class BottomBarScreen(val route: String) {
    object Home : BottomBar(
        route = "home",
        titleResId = R.string.home,
        icon = R.drawable.home,

        iconFocused = R.drawable.home
    )

    object Cart : BottomBar(
        route = "cart",
        titleResId = R.string.cart,
        icon = R.drawable.cart,
        iconFocused = R.drawable.cart
    )

    object Profile : BottomBar(
        route = "profile",
        titleResId =R.string.profile,
        icon = R.drawable.user,
        iconFocused = R.drawable.user
    )
}