package com.flexath.findit.core.presentation

import com.flexath.findit.core.utils.NavGraphConstants
import com.flexath.findit.core.utils.NavGraphConstants.AUTH_SUB_GRAPH
import com.flexath.findit.core.utils.NavGraphConstants.CATEGORY_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.HOME_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.MAIN_SUB_GRAPH
import com.flexath.findit.core.utils.NavGraphConstants.NAV_ARG_ID
import com.flexath.findit.core.utils.NavGraphConstants.NEWS_DETAIL_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.NEWS_LIST_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.ORDER_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.PRODUCT_DETAIL_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.PROFILE_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.REVIEW_PRODUCT_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.SEARCH_IN_STORE_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.SEARCH_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.SELLER_INFO_SCREEN
import com.flexath.findit.core.utils.NavGraphConstants.WISHLIST_SCREEN

sealed class Route(val route: String) {
    // Screens
    data object AuthStartDestination : Route(NavGraphConstants.AUTH_START_DESTINATION)
    data object MainStartDestination : Route(NavGraphConstants.MAIN_START_DESTINATION)

    data object HomeScreen : Route(HOME_SCREEN)
    data object WishlistScreen : Route(WISHLIST_SCREEN)
    data object OrderScreen : Route(ORDER_SCREEN)
    data object ProfileScreen : Route(PROFILE_SCREEN)
    data object SearchScreen : Route(SEARCH_SCREEN)
    data object CategoryScreen : Route(CATEGORY_SCREEN)
    data object ProductDetailScreen : Route(route = "${PRODUCT_DETAIL_SCREEN}/{${NAV_ARG_ID}}") {
        fun passId(id: Int): String {
            return "${PRODUCT_DETAIL_SCREEN}/$id"
        }
    }

    data object SellerInfoScreen : Route(SELLER_INFO_SCREEN)
    data object SearchInStoreScreen : Route(SEARCH_IN_STORE_SCREEN)
    data object ReviewProductScreen : Route(REVIEW_PRODUCT_SCREEN)
    data object NewsListScreen : Route(NEWS_LIST_SCREEN)
    data object NewsDetailScreen : Route(NEWS_DETAIL_SCREEN)

    // sub-graphs
    data object AuthSubGraph : Route(AUTH_SUB_GRAPH)
    data object MainSubGraph : Route(MAIN_SUB_GRAPH)
}