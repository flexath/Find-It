package com.flexath.findit.main.presentation.screens.seller

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.core.utils.Dimens.ExtraLargePadding5_2x
import com.flexath.findit.core.utils.Dimens.LargePadding10
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.core.utils.Dimens.SmallPadding3
import com.flexath.findit.core.utils.Dimens.SmallPadding5
import com.flexath.findit.main.presentation.screens.common.CustomFilledButton
import com.flexath.findit.main.presentation.screens.common.CustomOutlinedButton
import com.flexath.findit.main.presentation.screens.common.DetailTopAppBarWithTwoActions
import com.flexath.findit.main.presentation.screens.common.ProductCardGridList
import com.flexath.findit.main.presentation.screens.common.RatingTextWithIcon
import com.flexath.findit.main.presentation.screens.common.bottom_sheet.ShippingSupportContentBottomSheet
import com.flexath.findit.main.presentation.screens.common.bottom_sheet.SortingContentBottomSheet
import com.flexath.findit.main.presentation.view_model.ProductViewModel
import com.flexath.findit.theme.colorBackground
import com.flexath.findit.theme.searchBarBackgroundColor
import com.flexath.findit.theme.textColorPrimary
import com.flexath.findit.theme.textColorSecondary

@Composable
fun SellerInfoScreen(
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickProductCard: (Int) -> Unit,
    onClickSearchButton: () -> Unit,
    productViewModel: ProductViewModel
) {
    LaunchedEffect(key1 = Unit) {
        productViewModel.fetchAllProducts()
    }

    val productListState = productViewModel.productListState.collectAsStateWithLifecycle()

    var sortingBottomSheetShow by rememberSaveable {
        mutableStateOf(false)
    }

    var shippingSupportBottomSheetShow by rememberSaveable {
        mutableStateOf(false)
    }

    SortingContentBottomSheet(
        bottomSheetShow = sortingBottomSheetShow,
        onSheetShowChange = {
            sortingBottomSheetShow = it
        },
        onClickApplyButton = {
            sortingBottomSheetShow = false
        }
    )

    ShippingSupportContentBottomSheet(
        bottomSheetShow = shippingSupportBottomSheetShow,
        onSheetShowChange = {
            shippingSupportBottomSheetShow = it
        }
    )

    Box(
        modifier = modifier.background(color = colorBackground)
    ) {

        Column {
            DetailTopAppBarWithTwoActions(
                title = stringResource(R.string.lbl_seller_info),
                firstActionIcon = R.drawable.ic_search,
                secondActionIcon = R.drawable.ic_cart,
                onClickBackButton = {
                    onClickBackButton()
                },
                onClickFirstActionButton = {
                    onClickSearchButton()
                },
                onClickSecondActionButton = {

                }
            )

            LazyColumn {
                item {
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
                                modifier = Modifier.padding(start = MediumPadding5)
                            )

                            Spacer(modifier = Modifier.height(SmallPadding3))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = MediumPadding5)
                            ) {
                                Text(
                                    text = "Official Store",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = textColorPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )

                                Spacer(modifier = Modifier.width(SmallPadding3))

                                Image(
                                    painter = painterResource(id = R.drawable.ic_shield),
                                    contentDescription = "Certified Seller"
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RatingTextWithIcon(rating = 4.0f)
                        }
                    }

                    Spacer(modifier = Modifier.height(MediumPadding5))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = LargePadding2)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = "Location Icon"
                        )

                        Text(
                            text = "Jawa Barat, Bandung (Jam Buka 08:00-21:00)",
                            style = MaterialTheme.typography.bodySmall,
                            color = textColorPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = SmallPadding5)
                        )
                    }

                    Spacer(modifier = Modifier.height(MediumPadding5))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LargePadding2)
                    ) {
                        Column {
                            Text(
                                text = "Pengikut",
                                style = MaterialTheme.typography.bodyMedium,
                                color = textColorSecondary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(SmallPadding5))

                            Text(
                                text = "23 Rb",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = textColorPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )
                        }

                        Column {
                            Text(
                                text = "Produk",
                                style = MaterialTheme.typography.bodyMedium,
                                color = textColorSecondary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(SmallPadding5))

                            Text(
                                text = "150 Item",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = textColorPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )
                        }

                        Column {
                            Text(
                                text = "Bergabung",
                                style = MaterialTheme.typography.bodyMedium,
                                color = textColorSecondary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(SmallPadding3))

                            Text(
                                text = "20 Okt 2021",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = textColorPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(LargePadding10))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            shippingSupportBottomSheetShow = true
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.lbl_shipping_support),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            color = textColorPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(start = MediumPadding5)
                                .weight(1f)
                        )

                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_forward),
                                contentDescription = "Forward Icon")
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.background(color = searchBarBackgroundColor)
                    ) {
                        Spacer(modifier = Modifier.height(LargePadding2))

                        ProductCardGridList(
                            modifier = Modifier.fillMaxWidth(),
                            onClickProductCard = { id ->
                                onClickProductCard(id)
                            },
                            onClickVerticalDots = {

                            },
                            productList = productListState.value.productList
                        )

                        Spacer(modifier = Modifier.height(ExtraLargePadding5_2x))
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(LargePadding2)
                .align(Alignment.BottomCenter)
        ) {
            CustomOutlinedButton(
                text = stringResource(R.string.lbl_sorting),
                modifier = Modifier.weight(1f)
            ) {
                sortingBottomSheetShow = true
            }

            Spacer(modifier = Modifier.width(MediumPadding5))

            CustomFilledButton(
                text = stringResource(R.string.lbl_follow),
                modifier = Modifier.weight(1f),
                onClick = {

                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SellerInfoScreenPreview() {
    SellerInfoScreen(
        modifier = Modifier.fillMaxSize(),
        onClickBackButton = {

        },
        onClickProductCard = {

        },
        onClickSearchButton = {

        },
        productViewModel = hiltViewModel()
    )
}