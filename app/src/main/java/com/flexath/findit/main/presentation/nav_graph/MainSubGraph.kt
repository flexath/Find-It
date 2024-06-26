package com.flexath.findit.main.presentation.nav_graph

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.flexath.findit.main.presentation.screens.MainBottomBar
import com.flexath.findit.main.presentation.screens.MainTopBar
import com.flexath.findit.main.presentation.screens.category.CategoryScreen
import com.flexath.findit.main.presentation.screens.home.HomeScreen
import com.flexath.findit.main.presentation.screens.home.ProductDetailScreen
import com.flexath.findit.main.presentation.screens.order.OrderScreen
import com.flexath.findit.main.presentation.screens.profile.ProfileScreen
import com.flexath.findit.main.presentation.screens.review.ReviewProductScreen
import com.flexath.findit.main.presentation.screens.search.SearchScreen
import com.flexath.findit.main.presentation.screens.seller.SearchInStoreScreen
import com.flexath.findit.main.presentation.screens.seller.SellerInfoScreen
import com.flexath.findit.main.presentation.screens.wishlist.WishlistScreen
import com.flexath.findit.main.presentation.view_model.ProductViewModel
import com.flexath.findit.main.presentation.view_model.SearchViewModel
import com.flexath.findit.news.domain.model.ArticleVO
import com.flexath.findit.news.presentation.screens.NewsDetailScreen
import com.flexath.findit.news.presentation.screens.NewsListScreen
import com.flexath.findit.news.presentation.view_models.NewsViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
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

        SharedTransitionLayout {
            NavHost(
                navController = navHostController,
                startDestination = Route.HomeScreen.route,
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = bottomPadding, top = topPadding)
            ) {
                composable(
                    route = Route.HomeScreen.route
                ) {
                    val productViewModel: ProductViewModel = hiltViewModel()
                    val newsViewModel: NewsViewModel = hiltViewModel()

                    HomeScreen(
                        productViewModel = productViewModel,
                        newsViewModel = newsViewModel,
                        animatedVisibilityScope = this,
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
                        onClickArticleCard = { article ->
                            article.let {
                                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                    "article",
                                    article
                                )
                            }

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
                    val searchViewModel: SearchViewModel = hiltViewModel()
                    val productViewModel: ProductViewModel = hiltViewModel()

                    SearchScreen(
                        productViewModel = productViewModel,
                        searchViewModel = searchViewModel,
                        animatedVisibilityScope = this,
                        event = { event ->
                            searchViewModel.onProductEvent(event)
                        },
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
                    val productViewModel: ProductViewModel = hiltViewModel()
                    val categoryName = navBackStack.arguments?.getString(NAV_ARG_CATEGORY_NAME) ?: ""

                    CategoryScreen(
                        categoryName = categoryName,
                        productViewModel = productViewModel,
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
                    val productViewModel: ProductViewModel = hiltViewModel()

                    ProductDetailScreen(
                        productViewModel = productViewModel,
                        productId = navBackStack.arguments?.getInt(NAV_ARG_ID) ?: 0,
                        animatedVisibilityScope = this,
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
                    val productViewModel: ProductViewModel = hiltViewModel()

                    SellerInfoScreen(
                        modifier = Modifier.fillMaxSize(),
                        productViewModel = productViewModel,
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
                    val searchViewModel: SearchViewModel = hiltViewModel()
                    val productViewModel: ProductViewModel = hiltViewModel()

                    SearchInStoreScreen(
                        productViewModel = productViewModel,
                        searchViewModel = searchViewModel,
                        animatedVisibilityScope = this,
                        event = { event ->
                            searchViewModel.onProductEvent(event)
                        },
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
                    val newsViewModel: NewsViewModel = hiltViewModel()

                    LaunchedEffect(key1 = Unit) {
                        newsViewModel.fetchAllNews()
                    }

                    NewsListScreen(
                        newsViewModel = newsViewModel,
                        event = { event ->
                            newsViewModel.onNewsEvent(event = event)
                        },
                        context = context,
                        modifier = Modifier.fillMaxSize(),
                        onClickBackButton = {
                            navHostController.popBackStack()
                        },
                        onClickArticleCard = { articleVO ->
                            articleVO.let { article ->
                                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                    "article",
                                    article
                                )
                            }

                            navHostController.navigate(Route.NewsDetailScreen.route)
                        }
                    )
                }

                composable(
                    route = Route.NewsDetailScreen.route
                ) {
                    val newsViewModel: NewsViewModel = hiltViewModel()

                    navHostController.previousBackStackEntry?.savedStateHandle?.get<ArticleVO>("article")
                        ?.let { article ->
                            NewsDetailScreen(
                                article = article,
                                newsViewModel = newsViewModel,
                                context = context,
                                modifier = Modifier.fillMaxSize(),
                                onClickBackButton = {
                                    navHostController.popBackStack()
                                },
                                onClickSeeAllNewsButton = {
                                    navHostController.navigate(Route.NewsListScreen.route)
                                },
                                onClickArticleCard = { articleDetail ->
                                    articleDetail.let { articleDetail2 ->
                                        navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                            "article",
                                            articleDetail2
                                        )
                                    }

                                    navHostController.navigate(Route.NewsDetailScreen.route)
                                }
                            )
                        }
                }
            }
        }
    }
}