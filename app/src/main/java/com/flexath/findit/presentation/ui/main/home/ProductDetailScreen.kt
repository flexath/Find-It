package com.flexath.findit.presentation.ui.main.home

import android.content.Context
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.flexath.findit.presentation.theme.alertColor
import com.flexath.findit.presentation.theme.colorBackground
import com.flexath.findit.presentation.theme.dividerColor
import com.flexath.findit.presentation.theme.searchBarBackgroundColor
import com.flexath.findit.presentation.theme.textColorPrimary
import com.flexath.findit.presentation.ui.main.common.CustomFilledButton
import com.flexath.findit.presentation.ui.main.common.CustomFilledButtonWithIcon
import com.flexath.findit.presentation.ui.main.common.CustomOutlinedButton
import com.flexath.findit.presentation.ui.main.common.DetailTopAppBarWithTwoActions
import com.flexath.findit.presentation.ui.main.common.ProductItemSection
import com.flexath.findit.presentation.ui.main.common.RatingTextWithIcon
import com.flexath.findit.presentation.ui.main.common.reviewCardList
import com.flexath.findit.presentation.ui.main.common.bottom_sheet.AddToCartContentBottomSheet
import com.flexath.findit.presentation.ui.main.common.bottom_sheet.ProductContentBottomSheet
import com.flexath.findit.presentation.utils.Dimens
import com.flexath.findit.presentation.utils.Dimens.ExtraLargePadding5_2x
import com.flexath.findit.presentation.utils.Dimens.LargePadding2
import com.flexath.findit.presentation.utils.Dimens.MediumPadding5
import com.flexath.findit.presentation.utils.Dimens.SellerProfileWidth
import com.flexath.findit.presentation.utils.Dimens.SmallPadding3
import com.flexath.findit.presentation.utils.Dimens.SmallPadding5

@Composable
fun ProductDetailScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickSellerProfile: () -> Unit,
    onClickSeeAllReviewButton: () -> Unit,
    onClickProductCard: () -> Unit,
) {
    var productActionBottomSheetShow by rememberSaveable {
        mutableStateOf(false)
    }

    var addToCartBottomSheetShow by rememberSaveable {
        mutableStateOf(false)
    }

    AddToCartContentBottomSheet(
        bottomSheetShow = addToCartBottomSheetShow,
        onSheetShowChange = {
            addToCartBottomSheetShow = it
        },
        onClickAddToCardButton = {
            addToCartBottomSheetShow = false
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

    Box(
        modifier = modifier.background(color = colorBackground)
    ) {

        Column {
            DetailTopAppBarWithTwoActions(
                title = stringResource(R.string.lbl_detail_product),
                firstActionIcon = R.drawable.ic_share,
                secondActionIcon = R.drawable.ic_cart,
                onClickBackButton = {
                    onClickBackButton()
                },
                onClickFirstActionButton = {

                },
                onClickSecondActionButton = {

                }
            )

            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(LargePadding2))

                    Image(
                        painter = painterResource(id = R.drawable.dummy_earphone),
                        contentDescription = "Product Cover",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LargePadding2)
                            .aspectRatio(325f / 300f)
                            .clip(RoundedCornerShape(SmallPadding5))
                            .background(color = searchBarBackgroundColor)
                            .padding(SmallPadding5)
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    Text(
                        text = "TMA-2HD Wireless",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = textColorPrimary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LargePadding2),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(SmallPadding5))

                    Text(
                        text = "Rp 1.500.000",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Medium,
                        ),
                        color = alertColor,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LargePadding2)
                    )

                    Spacer(modifier = Modifier.height(SmallPadding5))

                    Row(
                        modifier = Modifier.padding(horizontal = LargePadding2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                            contentDescription = "Rating Star",
                            tint = Color(0xFFFFC120)
                        )

                        Text(
                            text = "4.6",
                            style = MaterialTheme.typography.bodyMedium,
                            color = textColorPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = Dimens.SmallPadding1)
                        )

                        Text(
                            text = "86 Reviews",
                            style = MaterialTheme.typography.bodyMedium,
                            color = textColorPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = SmallPadding5)
                        )
                    }

                    Spacer(modifier = Modifier.height(LargePadding2))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = LargePadding2),
                        thickness = Dimens.SmallPadding0,
                        color = dividerColor
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onClickSellerProfile()
                            }
                            .padding(horizontal = LargePadding2)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dummy_seller_profile),
                            contentDescription = "Seller Cover",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(SellerProfileWidth)
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

                        IconButton(
                            onClick = {
                                onClickSellerProfile()
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_forward),
                                contentDescription = "Forward Button"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(LargePadding2))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = LargePadding2),
                        thickness = Dimens.SmallPadding0,
                        color = dividerColor
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    Text(
                        text = stringResource(R.string.lbl_product_description),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Medium,
                        ),
                        color = textColorPrimary,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LargePadding2)
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    Text(
                        text = "The speaker unit contains a diaphragm that is precision-grown from NAC Audio bio-cellulose, making it stiffer, lighter and stronger than regular PET speaker units, and allowing the sound-producing diaphragm to vibrate without the levels of distortion found in other speakers. \n\nThe speaker unit contains a diaphragm that is precision-grown from NAC Audio bio-cellulose, making it stiffer, lighter and stronger than regular PET speaker units, and allowing the sound-producing diaphragm to vibrate without the levels of distortion found in other speakers. \n\nThe speaker unit contains a diaphragm that is precision-grown from NAC Audio bio-cellulose, making it stiffer, lighter and stronger than regular PET speaker units, and allowing the sound-producing diaphragm to vibrate without the levels of distortion found in other speakers. \n\nThe speaker unit contains a diaphragm that is precision-grown from NAC Audio bio-cellulose, making it stiffer, lighter and stronger than regular PET speaker units, and allowing the sound-producing diaphragm to vibrate without the levels of distortion found in other speakers. ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = textColorPrimary,
                        modifier = Modifier.padding(horizontal = LargePadding2)
                    )

                    Spacer(modifier = Modifier.height(LargePadding2))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = LargePadding2
                            )
                    ) {
                        Text(
                            text = "Review (86)",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Medium,
                            ),
                            color = textColorPrimary
                        )

                        RatingTextWithIcon(rating = 4.7f)
                    }
                }

                reviewCardList(

                )

                item {
                    Spacer(modifier = Modifier.height(SmallPadding5))

                    CustomOutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LargePadding2),
                        text = stringResource(R.string.lbl_see_all_review)
                    ) {
                        onClickSeeAllReviewButton()
                    }

                    Spacer(modifier = Modifier.height(LargePadding2))

                    Column(
                        modifier = Modifier.background(color = searchBarBackgroundColor)
                    ) {
                        Spacer(modifier = Modifier.height(LargePadding2))

                        ProductItemSection(
                            context = context,
                            title = stringResource(id = R.string.lbl_featured_product),
                            onClickSeeAll = {

                            },
                            onClickProductCard = {
                                onClickProductCard()
                            },
                            onClickVerticalDots = {
                                productActionBottomSheetShow = true
                            }
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
            CustomFilledButtonWithIcon(
                text = stringResource(R.string.lbl_added),
                modifier = Modifier.weight(1f)
            ) {

            }

            Spacer(modifier = Modifier.width(MediumPadding5))

            CustomFilledButton(
                text = stringResource(R.string.lbl_add_to_cart),
                modifier = Modifier.weight(1f)
            ) {
                addToCartBottomSheetShow = true
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProductDetailScreenPreview() {
    ProductDetailScreen(
        context = LocalContext.current,
        modifier = Modifier.fillMaxSize(),
        onClickBackButton = {

        },
        onClickSellerProfile = {

        },
        onClickSeeAllReviewButton = {

        },
        onClickProductCard = {

        }
    )
}