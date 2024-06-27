package com.flexath.findit.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.flexath.auth.presentation.nav_graph.AuthSubGraph
import com.flexath.core.presentation.Route
import com.flexath.findit.major.presentation.nav_graph.MainSubGraph

@Composable
fun NavGraph(
    startDestination: String
) {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        navigation(
            startDestination = Route.AuthStartDestination.route,
            route = Route.AuthSubGraph.route
        ) {
            composable(
                route = Route.AuthStartDestination.route
            ) {
                AuthSubGraph()
            }
        }

        navigation(
            startDestination = Route.MainStartDestination.route,
            route = Route.MainSubGraph.route
        ) {
            composable(
                route = Route.MainStartDestination.route
            ) {
                MainSubGraph()
            }
        }
    }
}