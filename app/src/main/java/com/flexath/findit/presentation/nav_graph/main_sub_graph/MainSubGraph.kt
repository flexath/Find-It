package com.flexath.findit.presentation.nav_graph.main_sub_graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flexath.findit.presentation.nav_graph.Route
import com.flexath.findit.presentation.ui.main.MainBottomBar
import com.flexath.findit.presentation.ui.main.MainTopBar
import com.flexath.findit.presentation.ui.main.category.CategoryScreen
import com.flexath.findit.presentation.ui.main.home.HomeScreen
import com.flexath.findit.presentation.ui.main.home.ProductDetailScreen
import com.flexath.findit.presentation.ui.main.news.NewsDetailScreen
import com.flexath.findit.presentation.ui.main.news.NewsListScreen
import com.flexath.findit.presentation.ui.main.order.OrderScreen
import com.flexath.findit.presentation.ui.main.profile.ProfileScreen
import com.flexath.findit.presentation.ui.main.review.ReviewProductScreen
import com.flexath.findit.presentation.ui.main.search.SearchScreen
import com.flexath.findit.presentation.ui.main.seller.SearchInStoreScreen
import com.flexath.findit.presentation.ui.main.seller.SellerInfoScreen
import com.flexath.findit.presentation.ui.main.wishlist.WishlistScreen

@Composable
fun MainSubGraph() {
    val navHostController = rememberNavController()
    val backStackEntry = navHostController.currentBackStackEntryAsState().value

//    val bottomBarVisibility = remember(key1 = backStackEntry) {
//        backStackEntry?.destination?.route == Route.HomeScreen.route ||
//                backStackEntry?.destination?.route == Route.WishlistScreen.route ||
//                backStackEntry?.destination?.route == Route.OrderScreen.route ||
//                backStackEntry?.destination?.route == Route.ProfileScreen.route
//    }

    val bottomBarVisibility by remember(key1 = backStackEntry?.destination?.route) {
        derivedStateOf {
            when(backStackEntry?.destination?.route) {
                Route.HomeScreen.route -> true
                Route.WishlistScreen.route -> true
                Route.OrderScreen.route -> true
                Route.ProfileScreen.route -> true
                else -> false
            }
        }
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
        val topPadding = it.calculateTopPadding()
        val context = LocalContext.current

        NavHost(
            navController = navHostController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding, top = topPadding)
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {
                HomeScreen(
                    context = context,
                    onClickCategory = {
                        navHostController.navigate(Route.CategoryScreen.route)
                    },
                    onClickProductCard = {
                        navHostController.navigate(Route.ProductDetailScreen.route)
                    },
                    onClickArticleCard = {
                        navHostController.navigate(Route.NewsDetailScreen.route)
                    },
                    onClickSeeAllNewsButton = {
                        navHostController.navigate(Route.NewsListScreen.route)
                    },
                    onClickSearchBar = {
                        navHostController.navigate(Route.SearchScreen.route)
                    }
                )
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

            composable(
                route = Route.SearchScreen.route
            ) {
                SearchScreen(
                    context = context,
                    modifier = Modifier.fillMaxSize(),
                    onClickBackButton = {
                        navHostController.popBackStack()
                    },
                    onClickProductCard = {
                        navHostController.navigate(Route.ProductDetailScreen.route)
                    }
                )
            }

            composable(
                route = Route.CategoryScreen.route
            ) {
                // Need to adjust for nested scrolling
                CategoryScreen(
                    context = context,
                    modifier = Modifier.fillMaxSize(),
                    onClickBackButton = {
                        navHostController.popBackStack()
                    }
                )
            }

            composable(
                route = Route.ProductDetailScreen.route
            ) {
                ProductDetailScreen(
                    context = context,
                    modifier = Modifier.fillMaxSize(),
                    onClickBackButton = {
                        navHostController.popBackStack()
                    },
                    onClickSellerProfile = {
                        navHostController.navigate(Route.SellerInfoScreen.route)
                    },
                    onClickSeeAllReviewButton = {
                        navHostController.navigate(Route.ReviewProductScreen.route)
                    },
                    onClickProductCard = {
                        navHostController.navigate(Route.ProductDetailScreen.route)
                    }
                )
            }

            composable(
                route = Route.SellerInfoScreen.route
            ) {
                SellerInfoScreen(
                    modifier = Modifier.fillMaxSize(),
                    onClickProductCard = {
                        navHostController.navigate(Route.ProductDetailScreen.route)
                    },
                    onClickBackButton = {
                        navHostController.popBackStack()
                    },
                    onClickSearchButton = {
                        navHostController.navigate(Route.SearchInStoreScreen.route)
                    }
                )
            }

            composable(
                route = Route.SearchInStoreScreen.route
            ) {
                SearchInStoreScreen(
                    context = context,
                    modifier = Modifier.fillMaxSize(),
                    onClickBackButton = {
                        navHostController.popBackStack()
                    },
                    onClickProductCard = {
                        navHostController.navigate(Route.ProductDetailScreen.route)
                    }
                )
            }

            composable(
                route = Route.ReviewProductScreen.route
            ) {
                ReviewProductScreen(
                    modifier = Modifier.fillMaxSize(),
                    onClickBackButton = {
                        navHostController.popBackStack()
                    }
                )
            }

            composable(
                route = Route.NewsListScreen.route
            ) {
                NewsListScreen(
                    context = context,
                    modifier = Modifier.fillMaxSize(),
                    onClickBackButton = {
                        navHostController.popBackStack()
                    },
                    onClickArticleCard = {
                        navHostController.navigate(Route.NewsDetailScreen.route)
                    }
                )
            }

            composable(
                route = Route.NewsDetailScreen.route
            ) {
                NewsDetailScreen(
                    modifier = Modifier.fillMaxSize(),
                    onClickBackButton = {
                        navHostController.popBackStack()
                    },
                    onClickSeeAllNewsButton = {
                        navHostController.navigate(Route.NewsListScreen.route)
                    },
                    onClickArticleCard = {
                        navHostController.navigate(Route.NewsDetailScreen.route)
                    }
                )
            }
        }
    }
}