package com.flexath.findit.presentation.ui.main.seller

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.presentation.theme.colorBackground
import com.flexath.findit.presentation.theme.textColorPrimary
import com.flexath.findit.presentation.ui.main.common.DetailTopAppBar
import com.flexath.findit.presentation.ui.main.common.ProductItemSection
import com.flexath.findit.presentation.ui.main.common.RatingTextWithIcon
import com.flexath.findit.presentation.ui.main.common.SearchBar
import com.flexath.findit.presentation.ui.main.search.components.historySearchList
import com.flexath.findit.presentation.utils.Dimens

@Composable
fun SearchInStoreScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickProductCard: () -> Unit
) {
    var query by remember {
        mutableStateOf("")
    }

    var hasSearch by remember {
        mutableStateOf(false)
    }

    hasSearch = remember {
        derivedStateOf {
            query.isNotEmpty()
        }.value
    }

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
                    Spacer(modifier = Modifier.height(Dimens.LargePadding2))

                    SearchBar(
                        context = context,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dimens.LargePadding2),
                        query = query,
                        isEnabled = true,
                        isClickable = false,
                        onClickSearchBar = {

                        },
                        onQueryChange = {
                            query = it
                        },
                        onSearch = {
                            hasSearch = true
                        }
                    )

                    Spacer(modifier = Modifier.height(Dimens.LargePadding2))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dimens.LargePadding2)
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

                    Spacer(modifier = Modifier.height(Dimens.LargePadding2))

                    if(!hasSearch) {
                        Column {
                            Spacer(modifier = Modifier.height(Dimens.LargePadding2))

                            Text(
                                text = stringResource(R.string.lbl_recent_search),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = textColorPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = Dimens.LargePadding2)
                            )
                        }
                    }
                }

                if(!hasSearch) {
                    historySearchList()
                }

            }
        }

        if(query.isEmpty()) {
            Column {
                Spacer(modifier = Modifier.height(Dimens.LargePadding2))

                ProductItemSection(
                    context = context,
                    title = stringResource(id = R.string.lbl_featured_product),
                    onClickSeeAll = {

                    },
                    onClickProductCard = {
                        onClickProductCard()
                    }
                )

                Spacer(modifier = Modifier.height(Dimens.LargePadding2))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchInStoreScreenPreview() {
    SearchInStoreScreen(
        context = LocalContext.current,
        onClickProductCard = {

        },
        onClickBackButton = {

        }
    )
}