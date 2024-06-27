package com.flexath.findit.major.presentation.screens.search

import android.content.Context
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flexath.core.utils.Dimens.ExtraLargePadding5_2x
import com.flexath.core.utils.Dimens.LargePadding2
import com.flexath.core.presentation.events.SearchEvent
import com.flexath.core.presentation.screens.components.DetailTopAppBar
import com.flexath.resources.R
import com.flexath.findit.major.presentation.screens.common.ProductCardGridList
import com.flexath.findit.major.presentation.screens.common.ProductItemSection
import com.flexath.findit.major.presentation.screens.common.bottom_sheet.ProductContentBottomSheet
import com.flexath.findit.major.presentation.screens.search.components.historySearchList
import com.flexath.findit.major.presentation.view_model.ProductViewModel
import com.flexath.findit.major.presentation.view_model.SearchViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SearchScreen(
    context: Context,
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClickBackButton: () -> Unit,
    onClickProductCard: (Int) -> Unit,
    event: (SearchEvent) -> Unit,
    productViewModel: ProductViewModel,
    searchViewModel: SearchViewModel
) {
    var historyList by rememberSaveable {
        mutableStateOf(listOf<com.flexath.findit.major.domain.model.HistoryVO>())
    }

    val searchState = searchViewModel.productSearchState.value
    historyList = searchViewModel.productSearchHistoryState.value.searchHistoryList

    LaunchedEffect(key1 = Unit) {
        productViewModel.fetchAllProducts()
    }

    val featuredProductListState = productViewModel.productListState.collectAsStateWithLifecycle()

    var productActionBottomSheetShow by rememberSaveable {
        mutableStateOf(false)
    }

    ProductContentBottomSheet(
        bottomSheetShow = productActionBottomSheetShow,
        onSheetShowChange = {
            productActionBottomSheetShow = it
        },
        onClickCloseButton = {
            productActionBottomSheetShow = false
        },
        onClickAddToCardButton = {
            productActionBottomSheetShow = false
        }
    )

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f)
        ){
            item {
                DetailTopAppBar(
                    title = stringResource(R.string.lbl_search),
                    onClickBackButton = {
                        onClickBackButton()
                    }
                )

                Spacer(modifier = Modifier.height(LargePadding2))

                com.flexath.core.presentation.common.TextFieldBar(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LargePadding2),
                    isEnabled = true,
                    query = searchState.query,
                    onQueryChange = { query ->
                        event(SearchEvent.UpdateQuery(query))
                    },
                    isClickable = false,
                    onClickSearchBar = {},
                    onSearch = { query ->
                        event(SearchEvent.Search)
                        if (query.isNotEmpty()) {
                            searchViewModel.insertSearchHistory(query)
                        }
                    }
                )
            }

            item {
                if(searchState.query.isEmpty()) {
                    Column {
                        Spacer(modifier = Modifier.height(LargePadding2))

                        Text(
                            text = stringResource(R.string.lbl_recent_search),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            color = com.flexath.resources.java.theme.textColorPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(horizontal = LargePadding2)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(LargePadding2))

                    ProductCardGridList(
                        modifier = Modifier.fillMaxWidth(),
                        productList = searchState.productList,
                        onClickVerticalDots = {

                        },
                        onClickProductCard = {
                            onClickProductCard(it)
                        }
                    )

                    Spacer(modifier = Modifier.height(ExtraLargePadding5_2x))
                }
            }

            if(searchState.query.isEmpty()) {
                historySearchList(
                    historyList = historyList,
                    onClickDeleteButton = { id ->
                        searchViewModel.deleteSearchHistory(id = id)
                    }
                )
            }
        }

        if(searchState.query.isEmpty()) {
            Column {
                Spacer(modifier = Modifier.height(LargePadding2))

                ProductItemSection(
                    context = context,
                    title = stringResource(id = R.string.lbl_featured_product),
                    onClickSeeAll = {

                    },
                    onClickProductCard = { id ->
                        onClickProductCard(id)
                    },
                    onClickVerticalDots = {
                        productActionBottomSheetShow = true
                    },
                    productItemList = featuredProductListState.value.productList,
                    animatedVisibilityScope = animatedVisibilityScope
                )

                Spacer(modifier = Modifier.height(LargePadding2))
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun SharedTransitionScope.SearchScreenPreview() {
//    SearchScreen(
//        context = LocalContext.current,
//        onClickBackButton = {
//
//        },
//        onClickProductCard = {
//
//        },
//        event = {
//
//        },
//        productViewModel = hiltViewModel(),
//        searchViewModel = hiltViewModel(),
//    )
//}