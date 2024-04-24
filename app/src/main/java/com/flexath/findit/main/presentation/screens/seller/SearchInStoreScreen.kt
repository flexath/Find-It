package com.flexath.findit.main.presentation.screens.seller

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.core.utils.Dimens.ExtraLargePadding5_2x
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.main.domain.model.HistoryVO
import com.flexath.findit.main.presentation.events.SearchEvent
import com.flexath.findit.main.presentation.screens.common.DetailTopAppBar
import com.flexath.findit.main.presentation.screens.common.ProductCardGridList
import com.flexath.findit.main.presentation.screens.common.ProductItemSection
import com.flexath.findit.main.presentation.screens.common.RatingTextWithIcon
import com.flexath.findit.main.presentation.screens.common.SearchBar
import com.flexath.findit.main.presentation.screens.common.bottom_sheet.ProductContentBottomSheet
import com.flexath.findit.main.presentation.screens.search.components.historySearchList
import com.flexath.findit.main.presentation.view_model.ProductViewModel
import com.flexath.findit.main.presentation.view_model.SearchViewModel
import com.flexath.findit.theme.colorBackground
import com.flexath.findit.theme.textColorPrimary

@Composable
fun SearchInStoreScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickProductCard: (Int) -> Unit,
    event: (SearchEvent) -> Unit,
    productViewModel: ProductViewModel,
    searchViewModel: SearchViewModel
) {
    var historyList by rememberSaveable {
        mutableStateOf(listOf<HistoryVO>())
    }

    val searchState = searchViewModel.productSearchState.value
    historyList = searchViewModel.productSearchHistoryState.value.searchHistoryList

    LaunchedEffect(key1 = Unit) {
        productViewModel.fetchAllProducts()
    }

    val featuredProductList = productViewModel.productListState.value.productList

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
        modifier = modifier.background(color = colorBackground)
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ){
            DetailTopAppBar(
                title = stringResource(R.string.lbl_search_in_store)
            ) {
                onClickBackButton()
            }

            LazyColumn {
                item {
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

                    Spacer(modifier = Modifier.height(LargePadding2))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LargePadding2)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dummy_seller_profile),
                            contentDescription = "Seller Cover",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(Dimens.SellerProfileWidth)
                                .aspectRatio(1f / 1f)
                                .clip(CircleShape)
                        )

                        Column(
                            modifier = Modifier.weight(3f)
                        ) {
                            Text(
                                text = "Shop Larson Electronic",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = textColorPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = Dimens.MediumPadding5)
                            )

                            Spacer(modifier = Modifier.height(Dimens.SmallPadding3))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = Dimens.MediumPadding5)
                            ) {
                                Text(
                                    text = "Official Store",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = textColorPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )

                                Spacer(modifier = Modifier.width(Dimens.SmallPadding3))

                                Image(
                                    painter = painterResource(id = R.drawable.ic_shield),
                                    contentDescription = "Certified Seller"
                                )
                            }
                        }

                        RatingTextWithIcon(rating = 3.5f)
                    }
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
                    historySearchList(historyList)
                }
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
                    productItemList = featuredProductList
                )

                Spacer(modifier = Modifier.height(LargePadding2))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchInStoreScreenPreview() {
    SearchInStoreScreen(
        context = LocalContext.current,
        onClickBackButton = {

        },
        onClickProductCard = {

        },
        event = {

        },
        productViewModel = hiltViewModel(),
        searchViewModel = hiltViewModel()
    )
}