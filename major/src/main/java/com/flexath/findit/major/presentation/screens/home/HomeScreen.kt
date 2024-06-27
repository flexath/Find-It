package com.flexath.findit.major.presentation.screens.home

import android.content.Context
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flexath.core.utils.Dimens
import com.flexath.core.utils.Dimens.LargePadding2
import com.flexath.core.utils.Dimens.LargePadding5
import com.flexath.core.presentation.screens.components.CustomOutlinedButton
import com.flexath.resources.R
import com.flexath.findit.major.presentation.screens.common.ProductItemSection
import com.flexath.findit.major.presentation.screens.common.bottom_sheet.CategoryContentBottomSheet
import com.flexath.findit.major.presentation.screens.common.bottom_sheet.ProductContentBottomSheet
import com.flexath.findit.major.presentation.screens.home.components.BannerSection
import com.flexath.findit.major.presentation.screens.home.components.ProductCategoryList
import com.flexath.findit.major.presentation.screens.home.components.TitleSection
import com.flexath.findit.major.presentation.view_model.ProductViewModel
import com.flexath.news.domain.model.ArticleVO
import com.flexath.news.presentation.screens.components.articleCardList
import com.flexath.news.presentation.view_models.NewsViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    context: Context,
    onClickCategory: (String) -> Unit,
    onClickProductCard: (Int) -> Unit,
    onClickArticleCard: (ArticleVO) -> Unit,
    onClickSeeAllNewsButton: () -> Unit,
    onClickSearchBar: () -> Unit,
    productViewModel: ProductViewModel,
    newsViewModel: NewsViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope
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

    val isProductListFetched = productViewModel.isProductListFetched.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = isProductListFetched) {
        if(!isProductListFetched.value) {
            productViewModel.fetchAllProductCategories()
            productViewModel.fetchAllProducts()
            newsViewModel.fetchNewsForHomeScreen()
        }
    }

    val productListState = productViewModel.productListState.collectAsStateWithLifecycle()
    val productCategoryListState = productViewModel.productCategoryListState.collectAsStateWithLifecycle()

    CategoryContentBottomSheet(
        categoryList = productCategoryListState.value.productCategoryList,
        bottomSheetShow = categoryBottomSheetShow,
        onSheetShowChange = {
            categoryBottomSheetShow = it
        },
        onClick = {
            onClickCategory(it)
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
        modifier = Modifier.background(com.flexath.resources.java.theme.colorBackground)
    ) {
        item {
            Column {
                Spacer(modifier = Modifier.height(LargePadding2))

                // Need to connect query with ViewModel
                com.flexath.core.presentation.common.TextFieldBar(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LargePadding2),
                    isEnabled = false,
                    query = query,
                    onQueryChange = {
                        query = it
                    },
                    isClickable = true,
                    onClickSearchBar = {
                        onClickSearchBar()
                    },
                    onSearch = {

                    },
                    isTrailingIconVisible = true
                )

                Spacer(modifier = Modifier.height(LargePadding2))

                BannerSection(pagerState = pagerState)

                Spacer(modifier = Modifier.height(LargePadding5))

                TitleSection(title = stringResource(R.string.lbl_categories)) {
                    categoryBottomSheetShow = true
                }

                ProductCategoryList(
                    categoryList = productCategoryListState.value.productCategoryList,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        onClickCategory(it)
                    }
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = com.flexath.resources.java.theme.searchBarBackgroundColor)
                ) {
                    Spacer(modifier = Modifier.height(LargePadding2))

                    ProductItemSection(
                        productItemList = if (productListState.value.productList.isNotEmpty()) {
                            productListState.value.productList.subList(0, 5)
                        } else {
                            emptyList()
                        },
                        context = context,
                        animatedVisibilityScope = animatedVisibilityScope,
                        title = stringResource(R.string.lbl_featured_product),
                        onClickSeeAll = {

                        },
                        onClickProductCard = {
                            onClickProductCard(it)
                        },
                        onClickVerticalDots = {
                            productActionBottomSheetShow = true
                        }
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    Image(
                        painter = painterResource(id = R.drawable.img_banner),
                        contentDescription = "Banner Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LargePadding2)
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
                            onClickProductCard(it)
                        },
                        onClickVerticalDots = {
                            productActionBottomSheetShow = true
                        },
                        productItemList = if (productListState.value.productList.isNotEmpty()) {
                            productListState.value.productList.subList(6, 11)
                        } else {
                            emptyList()
                        },
                        animatedVisibilityScope = animatedVisibilityScope
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
                            onClickProductCard(it)
                        },
                        onClickVerticalDots = {
                            productActionBottomSheetShow = true
                        },
                        productItemList = if (productListState.value.productList.isNotEmpty()) {
                            productListState.value.productList.subList(24, 30)
                        } else {
                            emptyList()
                        },
                        animatedVisibilityScope = animatedVisibilityScope
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    ProductItemSection(
                        context = context,
                        title = stringResource(R.string.lbl_top_rated_product),
                        onClickSeeAll = {

                        },
                        onClickProductCard = {
                            onClickProductCard(it)
                        },
                        onClickVerticalDots = {
                            productActionBottomSheetShow = true
                        },
                        productItemList = if (productListState.value.productList.isNotEmpty()) {
                            productListState.value.productList.subList(12, 17)
                        } else {
                            emptyList()
                        },
                        animatedVisibilityScope = animatedVisibilityScope
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    ProductItemSection(
                        context = context,
                        title = stringResource(R.string.lbl_special_offers),
                        onClickSeeAll = {

                        },
                        onClickProductCard = {
                            onClickProductCard(it)
                        },
                        onClickVerticalDots = {
                            productActionBottomSheetShow = true
                        },
                        productItemList = if (productListState.value.productList.isNotEmpty()) {
                            productListState.value.productList.subList(18, 23)
                        } else {
                            emptyList()
                        },
                        animatedVisibilityScope = animatedVisibilityScope
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
                color = com.flexath.resources.java.theme.textColorPrimary,
                modifier = Modifier.padding(horizontal = LargePadding2)
            )
        }

        articleCardList(
            context = context,
            articleList = newsViewModel.articleListHomeState.value.articleList,
            onClick = { article ->
                onClickArticleCard(article)
            }
        )

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

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//private fun HomeScreenPreview() {
//    HomeScreen(
//        context = LocalContext.current,
//        onClickCategory = {
//
//        },
//        onClickProductCard = {
//
//        },
//        onClickArticleCard = {
//
//        },
//        onClickSeeAllNewsButton = {
//
//        },
//        onClickSearchBar = {
//
//        },
//        productViewModel = hiltViewModel(),
//        newsViewModel = hiltViewModel(),
//        animatedVisibilityScope =
//    )
//}