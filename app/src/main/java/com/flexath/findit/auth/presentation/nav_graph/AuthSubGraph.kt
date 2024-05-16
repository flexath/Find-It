package com.flexath.findit.auth.presentation.nav_graph

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flexath.findit.R
import com.flexath.findit.core.presentation.Route
import com.flexath.findit.core.presentation.events.AppCoreEvent

@Composable
fun AuthSubGraph(
    onAuthEvent: (AppCoreEvent) -> Unit
) {
    val navHostController = rememberNavController()

    Scaffold(
        topBar = {
            IconButton(
                onClick = {
                    navHostController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back Button"
                )
            }
        }
    ) { paddingValues ->
        val padding = paddingValues.calculateBottomPadding()

        NavHost(
            navController = navHostController,
            startDestination = Route.RegisterScreen.route
        ) {
            composable(
                route = Route.RegisterScreen.route
            ) {

            }

            composable(
                route = Route.LoginScreen.route
            ) {

            }

            composable(
                route = Route.ResetPasswordScreen.route
            ) {

            }

            composable(
                route = Route.UpdatePasswordScreen.route
            ) {

            }

            composable(
                route = Route.VerificationScreen.route
            ) {

            }

            composable(
                route = Route.ProfileAndPasswordScreen.route
            ) {

            }
        }
    }
}