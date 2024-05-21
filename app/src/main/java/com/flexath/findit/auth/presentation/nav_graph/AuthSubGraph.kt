package com.flexath.findit.auth.presentation.nav_graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flexath.findit.auth.presentation.screens.RegisterScreen
import com.flexath.findit.auth.presentation.screens.VerificationScreen
import com.flexath.findit.auth.presentation.view_models.AuthViewModel
import com.flexath.findit.core.presentation.Route
import com.flexath.findit.core.presentation.events.AppCoreEvent

@Composable
fun AuthSubGraph(
    onAuthEvent: (AppCoreEvent) -> Unit,
    authViewModel: AuthViewModel
) {
    val navHostController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navHostController,
        startDestination = Route.RegisterScreen.route
    ) {
        composable(
            route = Route.RegisterScreen.route
        ) {
            RegisterScreen(
                viewModel = authViewModel,
                context = context,
                onClickRegisterButton = {
                    onAuthEvent(it)
                },
                modifier = Modifier.fillMaxSize()
            )
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
            VerificationScreen(
                viewModel = authViewModel,
                modifier = Modifier.fillMaxSize(),
                context = context,
                emailOrPhone = "09795448753",
                onClickConfirmButton = {

                }
            )
        }

        composable(
            route = Route.ProfileAndPasswordScreen.route
        ) {

        }
    }
}