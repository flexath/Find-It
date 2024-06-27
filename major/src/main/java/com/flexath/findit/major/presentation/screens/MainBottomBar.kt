package com.flexath.findit.major.presentation.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.flexath.resources.R
import com.flexath.core.utils.Dimens.SmallPadding4

@Composable
fun MainBottomBar(
    navHostController: NavHostController
) {
    val backStackEntry = navHostController.currentBackStackEntryAsState().value

    var selectedBottomItemIndex by remember {
        mutableIntStateOf(0)
    }

    selectedBottomItemIndex = when (backStackEntry?.destination?.route) {
        com.flexath.core.presentation.Route.HomeScreen.route -> 0
        com.flexath.core.presentation.Route.WishlistScreen.route -> 1
        com.flexath.core.presentation.Route.OrderScreen.route -> 2
        else -> 3
    }

    BottomAppBar(
        containerColor = com.flexath.resources.java.theme.colorBackground,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = SmallPadding4,
                spotColor = com.flexath.resources.java.theme.shadowColor
            )

    ) {
        bottomBarItemList.forEachIndexed { index, bottomBarItem ->
            NavigationBarItem(
                selected = selectedBottomItemIndex == index,
                onClick = {
                    selectedBottomItemIndex = index
                    navHostController.navigate(bottomBarItem.route) {
                        navHostController.graph.startDestinationRoute?.let { startDestinationRoute ->
                            popUpTo(startDestinationRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                label = {
                        Text(
                            text = bottomBarItem.label,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if(selectedBottomItemIndex == index) {
                                    FontWeight.Bold
                                } else {
                                    FontWeight.Medium
                                }
                            ),
                            color = if(selectedBottomItemIndex == index) {
                                com.flexath.resources.java.theme.colorPrimary
                            } else {
                                com.flexath.resources.java.theme.textColorPrimary
                            }
                        )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = bottomBarItem.icon),
                        tint = if(selectedBottomItemIndex == index) {
                            com.flexath.resources.java.theme.colorPrimary
                        } else {
                            com.flexath.resources.java.theme.textColorPrimary
                        },
                        contentDescription = bottomBarItem.label
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class BottomBarItem(
    val label: String,
    val route: String,
    @DrawableRes val icon: Int
)

val bottomBarItemList = listOf(
    BottomBarItem(
        label = "HOME",
        route = com.flexath.core.presentation.Route.HomeScreen.route,
        icon = R.drawable.ic_home
    ),
    BottomBarItem(
        label = "WISHLIST",
        route = com.flexath.core.presentation.Route.WishlistScreen.route,
        icon = R.drawable.ic_wishlist
    ),
    BottomBarItem(
        label = "ORDER",
        route = com.flexath.core.presentation.Route.OrderScreen.route,
        icon = R.drawable.ic_bag
    ),
    BottomBarItem(
        label = "PROFILE",
        route = com.flexath.core.presentation.Route.ProfileScreen.route,
        icon = R.drawable.ic_profile
    ),
)