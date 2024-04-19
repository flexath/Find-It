package com.flexath.findit.core.presentation

import com.flexath.findit.core.utils.NavGraphConstants

sealed class Route(val route: String) {
    // Screens
    data object AuthStartDestination: Route(NavGraphConstants.AUTH_START_DESTINATION)
    data object MainStartDestination: Route(NavGraphConstants.MAIN_START_DESTINATION)

    data object HomeScreen: Route(NavGraphConstants.HOME_SCREEN)
    data object WishlistScreen: Route(NavGraphConstants.WISHLIST_SCREEN)
    data object OrderScreen: Route(NavGraphConstants.ORDER_SCREEN)
    data object ProfileScreen: Route(NavGraphConstants.PROFILE_SCREEN)
    data object SearchScreen: Route(NavGraphConstants.SEARCH_SCREEN)
    data object CategoryScreen: Route(NavGraphConstants.CATEGORY_SCREEN)
    data object ProductDetailScreen: Route(NavGraphConstants.PRODUCT_DETAIL_SCREEN)
    data object SellerInfoScreen: Route(NavGraphConstants.SELLER_INFO_SCREEN)
    data object SearchInStoreScreen: Route(NavGraphConstants.SEARCH_IN_STORE_SCREEN)
    data object ReviewProductScreen: Route(NavGraphConstants.REVIEW_PRODUCT_SCREEN)
    data object NewsListScreen: Route(NavGraphConstants.NEWS_LIST_SCREEN)
    data object NewsDetailScreen: Route(NavGraphConstants.NEWS_DETAIL_SCREEN)

    // sub-graphs
    data object AuthSubGraph: Route(NavGraphConstants.AUTH_SUB_GRAPH)
    data object MainSubGraph: Route(NavGraphConstants.MAIN_SUB_GRAPH)
}