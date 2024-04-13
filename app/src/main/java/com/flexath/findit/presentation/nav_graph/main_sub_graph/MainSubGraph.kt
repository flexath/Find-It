package com.flexath.findit.presentation.nav_graph.main_sub_graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flexath.findit.presentation.nav_graph.Route
import com.flexath.findit.presentation.ui.main.MainBottomBar
import com.flexath.findit.presentation.ui.main.MainTopBar
import com.flexath.findit.presentation.ui.main.home.HomeScreen
import com.flexath.findit.presentation.ui.main.order.OrderScreen
import com.flexath.findit.presentation.ui.main.profile.ProfileScreen
import com.flexath.findit.presentation.ui.main.wishlist.WishlistScreen

@Composable
fun MainSubGraph() {
    val navHostController = rememberNavController()
    val backStackEntry = navHostController.currentBackStackEntryAsState().value

    val bottomBarVisibility = remember(key1 = backStackEntry) {
        backStackEntry?.destination?.route == Route.HomeScreen.route ||
                backStackEntry?.destination?.route == Route.WishlistScreen.route ||
                backStackEntry?.destination?.route == Route.OrderScreen.route ||
                backStackEntry?.destination?.route == Route.ProfileScreen.route
    }

    Scaffold(
        topBar = {
            if (bottomBarVisibility) {
                MainTopBar(
                    onClickCartIcon = {

                    },
                    onClickBellIcon = {

                    }
                )
            }
        },
        bottomBar = {
            if (bottomBarVisibility) {
                MainBottomBar(navHostController)
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()

        NavHost(
            navController = navHostController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding)
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {
                HomeScreen()
            }

            composable(
                route = Route.WishlistScreen.route
            ) {
                WishlistScreen()
            }

            composable(
                route = Route.OrderScreen.route
            ) {
                OrderScreen()
            }

            composable(
                route = Route.ProfileScreen.route
            ) {
                ProfileScreen()
            }
        }
    }
}