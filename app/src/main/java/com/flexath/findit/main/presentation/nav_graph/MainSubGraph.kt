package com.flexath.findit.main.presentation.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flexath.findit.core.presentation.Route
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.presentation.screens.MainBottomBar
import com.flexath.findit.main.presentation.screens.MainTopBar
import com.flexath.findit.main.presentation.screens.category.CategoryScreen
import com.flexath.findit.main.presentation.screens.home.HomeScreen
import com.flexath.findit.main.presentation.screens.home.ProductDetailScreen
import com.flexath.findit.main.presentation.screens.news.NewsDetailScreen
import com.flexath.findit.main.presentation.screens.news.NewsListScreen
import com.flexath.findit.main.presentation.screens.order.OrderScreen
import com.flexath.findit.main.presentation.screens.profile.ProfileScreen
import com.flexath.findit.main.presentation.screens.review.ReviewProductScreen
import com.flexath.findit.main.presentation.screens.search.SearchScreen
import com.flexath.findit.main.presentation.screens.seller.SearchInStoreScreen
import com.flexath.findit.main.presentation.screens.seller.SellerInfoScreen
import com.flexath.findit.main.presentation.screens.wishlist.WishlistScreen
import com.flexath.findit.main.presentation.view_model.ProductViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
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

        val productViewModel: ProductViewModel = hiltViewModel()

        val featureProductList: MutableState<List<ProductVO>> = rememberSaveable {
            mutableStateOf(listOf())
        }

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

                productViewModel.fetchAllProductCategories()
                productViewModel.fetchAllProducts()

//                LaunchedEffect(key1 = Unit) {
//                    featureProductList.value = if (productViewModel.productListState.value.productList.isNotEmpty()) {
//                        productViewModel.productListState.value.productList.subList(0, 5)
//                    } else {
//                        emptyList()
//                    }
//                }

                HomeScreen(
                    viewModel = productViewModel,
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
                var product by remember {
                    mutableStateOf(
                        ProductVO(
                            title = "Product Title",
                            price = 1,
                            rating = 0.0,
                            stock = 1,
                            brand = "Product Brand",
                            thumbnail = "Product Image Url",
                            images = emptyList()
                        )
                    )
                }

                productViewModel.fetchProduct(6)

                LaunchedEffect(key1 = Unit) { // Ensures launch only on first composition
                    launch {
                        productViewModel.productSharedFlow.collect { productResource ->
                            product = productResource
                        }
                    }
                }

                ProductDetailScreen(
                    product = product,
                    featuredProductList = listOf(),
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