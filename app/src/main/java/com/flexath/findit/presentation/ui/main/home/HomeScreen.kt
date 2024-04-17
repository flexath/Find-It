package com.flexath.findit.presentation.ui.main.home

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.presentation.theme.colorBackground
import com.flexath.findit.presentation.theme.textColorPrimary
import com.flexath.findit.presentation.ui.main.common.CustomOutlinedButton
import com.flexath.findit.presentation.ui.main.common.ProductItemSection
import com.flexath.findit.presentation.ui.main.common.SearchBar
import com.flexath.findit.presentation.ui.main.common.articleCardList
import com.flexath.findit.presentation.ui.main.home.components.BannerSection
import com.flexath.findit.presentation.ui.main.home.components.CategoryContentBottomSheet
import com.flexath.findit.presentation.ui.main.home.components.ProductCategoryList
import com.flexath.findit.presentation.ui.main.home.components.ProductContentBottomSheet
import com.flexath.findit.presentation.ui.main.home.components.TitleSection
import com.flexath.findit.presentation.utils.Dimens
import com.flexath.findit.presentation.utils.Dimens.LargePadding2
import com.flexath.findit.presentation.utils.Dimens.LargePadding5

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    context: Context,
    onClickCategory: () -> Unit,
    onClickProductCard: () -> Unit,
    onClickArticleCard: () -> Unit,
    onClickSeeAllNewsButton: () -> Unit,
    onClickSearchBar: () -> Unit
) {
    val pagerState = rememberPagerState { 3 }

    var categoryBottomSheetShow by rememberSaveable {
        mutableStateOf(false)
    }

    var productActionBottomSheetShow by rememberSaveable {
        mutableStateOf(false)
    }

    var query by rememberSaveable {
        mutableStateOf("")
    }

    CategoryContentBottomSheet(
        bottomSheetShow = categoryBottomSheetShow,
        onSheetShowChange = {
            categoryBottomSheetShow = it
        },
        onClick = {
            onClickCategory()
            categoryBottomSheetShow = false
        }
    )

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

    LazyColumn(
        modifier = Modifier.background(colorBackground)
    ) {
        item {
            Column {
                Spacer(modifier = Modifier.height(LargePadding2))

                // Need to connect query with ViewModel
                SearchBar(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LargePadding2),
                    isEnabled = false,
                    query = query,
                    isClickable = true,
                    onClickSearchBar = {
                        onClickSearchBar()
                    },
                    onQueryChange = {
                        query = it
                    },
                    onSearch = {

                    }
                )

                Spacer(modifier = Modifier.height(LargePadding2))

                BannerSection(pagerState = pagerState)

                Spacer(modifier = Modifier.height(LargePadding5))

                TitleSection(title = stringResource(R.string.lbl_categories)) {
                    categoryBottomSheetShow = true
                }

                ProductCategoryList(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    onClickCategory()
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFFAFAFA))
                ) {
                    Spacer(modifier = Modifier.height(LargePadding2))

                    ProductItemSection(
                        context = context,
                        title = stringResource(R.string.lbl_featured_product),
                        onClickSeeAll = {

                        },
                        onClickProductCard = {
                            onClickProductCard()
                        },
                        onClickVerticalDots = {
                            productActionBottomSheetShow = true
                        }
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    Image(
                        painter = painterResource(id = R.drawable.img_banner_4),
                        contentDescription = "Banner Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(horizontal = LargePadding2)
                            .fillMaxWidth()
                            .aspectRatio(21f / 10f)
                            .clip(RoundedCornerShape(Dimens.MediumPadding3))
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    ProductItemSection(
                        context = context,
                        title = stringResource(R.string.lbl_best_sellers),
                        onClickSeeAll = {

                        },
                        onClickProductCard = {
                            onClickProductCard()
                        },
                        onClickVerticalDots = {
                            productActionBottomSheetShow = true
                        }
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    Image(
                        painter = painterResource(id = R.drawable.img_banner_4),
                        contentDescription = "Banner Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(horizontal = LargePadding2)
                            .fillMaxWidth()
                            .aspectRatio(21f / 10f)
                            .clip(RoundedCornerShape(Dimens.MediumPadding3))
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    ProductItemSection(
                        context = context,
                        title = stringResource(R.string.lbl_new_arrivals),
                        onClickSeeAll = {

                        },
                        onClickProductCard = {
                            onClickProductCard()
                        },
                        onClickVerticalDots = {
                            productActionBottomSheetShow = true
                        }
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    ProductItemSection(
                        context = context,
                        title = stringResource(R.string.lbl_top_rated_product),
                        onClickSeeAll = {

                        },
                        onClickProductCard = {
                            onClickProductCard()
                        },
                        onClickVerticalDots = {
                            productActionBottomSheetShow = true
                        }
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    ProductItemSection(
                        context = context,
                        title = stringResource(R.string.lbl_special_offers),
                        onClickSeeAll = {

                        },
                        onClickProductCard = {
                            onClickProductCard()
                        },
                        onClickVerticalDots = {
                            productActionBottomSheetShow = true
                        }
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(LargePadding2))

            Text(
                text = stringResource(R.string.lbl_latest_news),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Medium,
                ),
                color = textColorPrimary,
                modifier = Modifier.padding(horizontal = LargePadding2)
            )
        }

        articleCardList {
            onClickArticleCard()
        }

        item {
            CustomOutlinedButton(
                text = stringResource(R.string.lbl_sell_all_news),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LargePadding2)
            ) {
                onClickSeeAllNewsButton()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        context = LocalContext.current,
        onClickCategory = {

        },
        onClickProductCard = {

        },
        onClickArticleCard = {

        },
        onClickSeeAllNewsButton = {

        },
        onClickSearchBar = {

        }
    )
}