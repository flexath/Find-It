package com.flexath.findit.main.presentation.nav_graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.flexath.findit.core.presentation.Route
import com.flexath.findit.core.utils.NavGraphConstants.NAV_ARG_CATEGORY_NAME
import com.flexath.findit.core.utils.NavGraphConstants.NAV_ARG_ID
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.presentation.screens.MainBottomBar
import com.flexath.findit.main.presentation.screens.MainTopBar
import com.flexath.findit.main.presentation.screens.category.CategoryScreen
import com.flexath.findit.main.presentation.screens.home.HomeScreen
import com.flexath.findit.main.presentation.screens.home.ProductDetailScreen
import com.flexath.findit.news.presentation.screens.NewsDetailScreen
import com.flexath.findit.news.presentation.screens.NewsListScreen
import com.flexath.findit.main.presentation.screens.order.OrderScreen
import com.flexath.findit.main.presentation.screens.profile.ProfileScreen
import com.flexath.findit.main.presentation.screens.review.ReviewProductScreen
import com.flexath.findit.main.presentation.screens.search.SearchScreen
import com.flexath.findit.main.presentation.screens.seller.SearchInStoreScreen
import com.flexath.findit.main.presentation.screens.seller.SellerInfoScreen
import com.flexath.findit.main.presentation.screens.wishlist.WishlistScreen
import com.flexath.findit.main.presentation.view_model.ProductViewModel

@Composable
fun MainSubGraph(
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val navHostController = rememberNavController()
    val backStackEntry = navHostController.currentBackStackEntryAsState().value

    val bottomBarVisibility = remember(key1 = backStackEntry) {
        backStackEntry?.destination?.route == Route.HomeScreen.route ||
                backStackEntry?.destination?.route == Route.WishlistScreen.route ||
                backStackEntry?.destination?.route == Route.OrderScreen.route ||
                backStackEntry?.destination?.route == Route.ProfileScreen.route
    }

//    val bottomBarVisibility by remember(key1 = backStackEntry?.destination?.route) {
//        derivedStateOf {
//            when (backStackEntry?.destination?.route) {
//                Route.HomeScreen.route -> true
//                Route.WishlistScreen.route -> true
//                Route.OrderScreen.route -> true
//                Route.ProfileScreen.route -> true
//                else -> false
//            }
//        }
//    }

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

                LaunchedEffect(key1 = Unit) {
                    productViewModel.fetchAllProductCategories()
                    productViewModel.fetchAllProducts()
                }

                HomeScreen(
                    viewModel = productViewModel,
                    context = context,
                    onClickCategory = { categoryName ->
                        navHostController.navigate(
                            Route.CategoryScreen.passCategoryName(
                                categoryName = categoryName
                            )
                        )
                    },
                    onClickProductCard = { id ->
                        navHostController.navigate(Route.ProductDetailScreen.passId(id = id))
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
                LaunchedEffect(key1 = Unit) {
                    productViewModel.fetchAllProducts()
                }

                val productList = productViewModel.productListState.value.productList

                SearchScreen(
                    productList = productList.shuffled(),
                    context = context,
                    modifier = Modifier.fillMaxSize(),
                    onClickBackButton = {
                        navHostController.popBackStack()
                    },
                    onClickProductCard = { id ->
                        navHostController.navigate(Route.ProductDetailScreen.passId(id))
                    }
                )
            }

            composable(
                route = Route.CategoryScreen.route,
                arguments = listOf(
                    navArgument(
                        name = NAV_ARG_CATEGORY_NAME
                    ) {
                        type = NavType.StringType
                    }
                )
            ) { navBackStack ->
                val categoryName = navBackStack.arguments?.getString(NAV_ARG_CATEGORY_NAME) ?: ""

                LaunchedEffect(key1 = Unit) {
                    productViewModel.fetchAllProductsOfCategory(categoryName)
                }
                val productList = productViewModel.productListOfCategoryState.value.productList

                // Need to adjust for nested scrolling
                CategoryScreen(
                    categoryName = categoryName,
                    productList = productList,
                    context = context,
                    modifier = Modifier.fillMaxSize(),
                    onClickProductCard = { id ->
                        navHostController.navigate(Route.ProductDetailScreen.passId(id))
                    },
                    onClickBackButton = {
                        navHostController.popBackStack()
                    }
                )
            }

            composable(
                route = Route.ProductDetailScreen.route,
                arguments = listOf(
                    navArgument(
                        name = NAV_ARG_ID
                    ) {
                        type = NavType.IntType
                    }
                )
            ) { navBackStack ->
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

                var productList by remember {
                    mutableStateOf(listOf<ProductVO>())
                }

                LaunchedEffect(key1 = Unit) {
                    productViewModel.fetchProduct(navBackStack.arguments?.getInt(NAV_ARG_ID) ?: 0)
                    productViewModel.fetchAllProducts()
                }

                productViewModel.productState.value.product?.let { productResult ->
                    product = productResult
                }
                productList = productViewModel.productListState.value.productList

                ProductDetailScreen(
                    product = product,
                    featuredProductList = productList.shuffled(),
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
                    onClickProductCard = { id ->
                        navHostController.navigate(Route.ProductDetailScreen.passId(id))
                    }
                )
            }

            composable(
                route = Route.SellerInfoScreen.route
            ) {
                LaunchedEffect(key1 = Unit) {
                    productViewModel.fetchAllProducts()
                }

                val productList = productViewModel.productListState.value.productList

                SellerInfoScreen(
                    modifier = Modifier.fillMaxSize(),
                    productList = productList,
                    onClickProductCard = { id ->
                        navHostController.navigate(Route.ProductDetailScreen.passId(id))
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
                LaunchedEffect(key1 = Unit) {
                    productViewModel.fetchAllProducts()
                }

                val productList = productViewModel.productListState.value.productList

                SearchInStoreScreen(
                    context = context,
                    productList = productList.shuffled(),
                    modifier = Modifier.fillMaxSize(),
                    onClickBackButton = {
                        navHostController.popBackStack()
                    },
                    onClickProductCard = { id ->
                        navHostController.navigate(Route.ProductDetailScreen.passId(id))
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