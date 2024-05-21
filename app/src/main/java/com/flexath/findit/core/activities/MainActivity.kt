package com.flexath.findit.core.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.flexath.findit.core.presentation.Route
import com.flexath.findit.core.presentation.nav_graph.NavGraph
import com.flexath.findit.core.presentation.view_model.AppViewModel
import com.flexath.findit.theme.FindItTheme
import com.flexath.findit.theme.colorBackground
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindItTheme(
                dynamicColor = false
            ) {
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemUiColor = rememberSystemUiController()

                val appViewModel: AppViewModel = hiltViewModel()
                val startSubGraph = appViewModel.startDestination

                SideEffect {
                    systemUiColor.setSystemBarsColor(
                        color = colorBackground,
                        darkIcons = !isSystemInDarkMode
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorBackground),
                    contentAlignment = Alignment.Center
                ) {
                    if(startSubGraph.isNotEmpty()) {
                        NavGraph(
                            startDestination = startSubGraph
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FindItTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorBackground),
            contentAlignment = Alignment.Center
        ) {
            NavGraph(startDestination = Route.MainSubGraph.route)
        }
    }
}