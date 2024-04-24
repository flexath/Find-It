package com.flexath.findit.main.presentation.screens.search

import android.content.Context
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens.ExtraLargePadding5_2x
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.presentation.events.SearchEvent
import com.flexath.findit.main.presentation.screens.common.DetailTopAppBar
import com.flexath.findit.main.presentation.screens.common.ProductCardGridList
import com.flexath.findit.main.presentation.screens.common.ProductItemSection
import com.flexath.findit.main.presentation.screens.common.SearchBar
import com.flexath.findit.main.presentation.screens.common.bottom_sheet.ProductContentBottomSheet
import com.flexath.findit.main.presentation.screens.search.components.historySearchList
import com.flexath.findit.main.presentation.states.ProductHistoryState
import com.flexath.findit.main.presentation.states.ProductSearchState
import com.flexath.findit.theme.textColorPrimary

@Composable
fun SearchScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickProductCard: (Int) -> Unit,
    productList: List<ProductVO>,
    searchState: ProductSearchState,
    event: (SearchEvent) -> Unit,
    searchHistoryState: ProductHistoryState
) {
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
                    title = stringResource(R.string.lbl_search)
                ) {
                    onClickBackButton()
                }

                Spacer(modifier = Modifier.height(LargePadding2))

                SearchBar(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LargePadding2),
                    query = searchState.query,
                    isEnabled = true,
                    isClickable = false,
                    onClickSearchBar = {},
                    onQueryChange = { query ->
                        event(SearchEvent.UpdateQuery(query))
                    },
                    onSearch = {
                        event(SearchEvent.Search)
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
                            color = textColorPrimary,
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
                historySearchList(historyList = searchHistoryState.searchHistoryList)
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
                    productItemList = productList
                )

                Spacer(modifier = Modifier.height(LargePadding2))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        context = LocalContext.current,
        onClickBackButton = {

        },
        onClickProductCard = {

        },
        productList = listOf(),
        searchState = ProductSearchState(),
        event = {

        },
        searchHistoryState = ProductHistoryState()
    )
}