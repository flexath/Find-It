package com.flexath.findit.presentation.nav_graph

import com.flexath.findit.presentation.utils.NavGraphConstants

sealed class Route(val route: String) {
    // Screens
    data object AuthStartDestination: Route(NavGraphConstants.AUTH_START_DESTINATION)
    data object MainStartDestination: Route(NavGraphConstants.MAIN_START_DESTINATION)

    data object HomeScreen: Route(NavGraphConstants.HOME_SCREEN)
    data object WishlistScreen: Route(NavGraphConstants.WISHLIST_SCREEN)
    data object OrderScreen: Route(NavGraphConstants.ORDER_SCREEN)
    data object ProfileScreen: Route(NavGraphConstants.PROFILE_SCREEN)
    data object NewsListScreen: Route(NavGraphConstants.NEWS_LIST_SCREEN)
    data object NewsDetailScreen: Route(NavGraphConstants.NEWS_DETAIL_SCREEN)

    // sub-graphs
    data object AuthSubGraph: Route(NavGraphConstants.AUTH_SUB_GRAPH)
    data object MainSubGraph: Route(NavGraphConstants.MAIN_SUB_GRAPH)
}