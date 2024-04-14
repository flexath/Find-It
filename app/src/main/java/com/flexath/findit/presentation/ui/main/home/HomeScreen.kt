package com.flexath.findit.presentation.ui.main.home

import android.widget.Toast
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
import androidx.compose.runtime.remember
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
import com.flexath.findit.presentation.ui.main.common.SearchBar
import com.flexath.findit.presentation.ui.main.home.components.BannerSection
import com.flexath.findit.presentation.ui.main.home.components.ProductCardList
import com.flexath.findit.presentation.ui.main.home.components.ProductCategoryList
import com.flexath.findit.presentation.ui.main.home.components.TitleSection
import com.flexath.findit.presentation.ui.main.home.components.articleCardList
import com.flexath.findit.presentation.utils.Dimens
import com.flexath.findit.presentation.utils.Dimens.LargePadding2
import com.flexath.findit.presentation.utils.Dimens.LargePadding5
import com.flexath.findit.presentation.utils.Dimens.SmallPadding4

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val pagerState = rememberPagerState { 3 }
    val context = LocalContext.current

    var query by remember {
        mutableStateOf("")
    }

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
                    query = query
                ) {
                    query = it
                }

                Spacer(modifier = Modifier.height(LargePadding2))

                BannerSection(pagerState = pagerState)

                Spacer(modifier = Modifier.height(LargePadding5))

                TitleSection(title = stringResource(R.string.lbl_categories)) {
                    // add SeeAll text button clicking codes
                    Toast.makeText(context, "SeeAll is clicked", Toast.LENGTH_SHORT).show()
                }

                ProductCategoryList(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFFAFAFA))
                ) {
                    Spacer(modifier = Modifier.height(LargePadding2))

                    TitleSection(title = stringResource(R.string.lbl_featured_product)) {
                        // add SeeAll text button clicking codes
                        Toast.makeText(context, "SeeAll is clicked", Toast.LENGTH_SHORT).show()
                    }

                    Spacer(modifier = Modifier.height(SmallPadding4))

                    ProductCardList(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClickProductCard = {

                        },
                        onClickVerticalDots = {

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

                    TitleSection(title = stringResource(R.string.lbl_best_sellers)) {
                        // add SeeAll text button clicking codes
                        Toast.makeText(context, "SeeAll is clicked", Toast.LENGTH_SHORT).show()
                    }

                    Spacer(modifier = Modifier.height(SmallPadding4))

                    ProductCardList(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClickProductCard = {

                        },
                        onClickVerticalDots = {

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

                    TitleSection(title = stringResource(R.string.lbl_new_arrivals)) {
                        // add SeeAll text button clicking codes
                        Toast.makeText(context, "SeeAll is clicked", Toast.LENGTH_SHORT).show()
                    }

                    Spacer(modifier = Modifier.height(SmallPadding4))

                    ProductCardList(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClickProductCard = {

                        },
                        onClickVerticalDots = {

                        }
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    TitleSection(title = stringResource(R.string.lbl_top_rated_product)) {
                        // add SeeAll text button clicking codes
                        Toast.makeText(context, "SeeAll is clicked", Toast.LENGTH_SHORT).show()
                    }

                    Spacer(modifier = Modifier.height(SmallPadding4))

                    ProductCardList(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClickProductCard = {

                        },
                        onClickVerticalDots = {

                        }
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    TitleSection(title = stringResource(R.string.lbl_special_offers)) {
                        // add SeeAll text button clicking codes
                        Toast.makeText(context, "SeeAll is clicked", Toast.LENGTH_SHORT).show()
                    }

                    Spacer(modifier = Modifier.height(SmallPadding4))

                    ProductCardList(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClickProductCard = {

                        },
                        onClickVerticalDots = {

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

        articleCardList {}
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}