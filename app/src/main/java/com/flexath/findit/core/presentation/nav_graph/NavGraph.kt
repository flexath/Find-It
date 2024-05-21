package com.flexath.findit.core.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.flexath.findit.auth.presentation.nav_graph.AuthSubGraph
import com.flexath.findit.auth.presentation.view_models.AuthViewModel
import com.flexath.findit.core.presentation.Route
import com.flexath.findit.core.presentation.view_model.AppViewModel
import com.flexath.findit.main.presentation.nav_graph.MainSubGraph

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
                val authViewModel: AuthViewModel = hiltViewModel()
                AuthSubGraph(
                    authViewModel = authViewModel,
                    onAuthEvent = {
                        authViewModel.onEvent(it)
                    }
                )
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