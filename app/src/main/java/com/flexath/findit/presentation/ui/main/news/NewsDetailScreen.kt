package com.flexath.findit.presentation.ui.main.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.presentation.theme.textColorPrimary
import com.flexath.findit.presentation.theme.textColorSecondary
import com.flexath.findit.presentation.ui.main.common.CustomOutlinedButton
import com.flexath.findit.presentation.ui.main.common.DetailTopAppBarWithOneAction
import com.flexath.findit.presentation.ui.main.home.components.articleCardList
import com.flexath.findit.presentation.utils.Dimens
import com.flexath.findit.presentation.utils.Dimens.LargePadding2

@Composable
fun NewsDetailScreen(
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickArticleCard: () -> Unit,
    onClickSeeAllNewsButton: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        DetailTopAppBarWithOneAction(
            title = stringResource(R.string.lbl_detail_news),
            onClickBackButton = {
                onClickBackButton()
            },
            onClickActionButton = {
                // Share news article code
            }
        )

        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(LargePadding2))

                Image(
                    painter = painterResource(id = R.drawable.dummy_car),
                    contentDescription = "Banner Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = LargePadding2)
                        .aspectRatio(13f / 8f)
                        .clip(RoundedCornerShape(Dimens.MediumPadding3))
                )

                Spacer(modifier = Modifier.height(LargePadding2))

                Text(
                    text = "Philosophy Tips Merawat Bodi Mobil agar Tidak Terlihat Kusam",
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = LargePadding2)
                )

                Spacer(modifier = Modifier.height(Dimens.SmallPadding5))

                Text(
                    text = "13 Jan 2021",
                    style = MaterialTheme.typography.bodySmall,
                    color = textColorSecondary,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = LargePadding2)
                )

                Spacer(modifier = Modifier.height(LargePadding2))

                Text(
                    text = "The speaker unit contains a diaphragm that is precision-grown from NAC Audio bio-cellulose, making it stiffer, lighter and stronger than regular PET speaker units, and allowing the sound-producing diaphragm to vibrate without the levels of distortion found in other speakers. \n\nThe speaker unit contains a diaphragm that is precision-grown from NAC Audio bio-cellulose, making it stiffer, lighter and stronger than regular PET speaker units, and allowing the sound-producing diaphragm to vibrate without the levels of distortion found in other speakers. \n\nThe speaker unit contains a diaphragm that is precision-grown from NAC Audio bio-cellulose, making it stiffer, lighter and stronger than regular PET speaker units, and allowing the sound-producing diaphragm to vibrate without the levels of distortion found in other speakers. \n\nThe speaker unit contains a diaphragm that is precision-grown from NAC Audio bio-cellulose, making it stiffer, lighter and stronger than regular PET speaker units, and allowing the sound-producing diaphragm to vibrate without the levels of distortion found in other speakers. ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColorPrimary,
                    modifier = Modifier.padding(horizontal = LargePadding2)
                )
            }

            item {
                Spacer(modifier = Modifier.height(LargePadding2))

                Text(
                    text = stringResource(R.string.lbl_other_news),
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
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun NewsDetailScreenPreview() {
    NewsDetailScreen(
        modifier = Modifier.fillMaxSize(),
        onClickArticleCard = {

        },
        onClickBackButton = {

        },
        onClickSeeAllNewsButton = {

        }
    )
}