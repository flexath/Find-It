package com.flexath.findit.major.presentation.screens.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flexath.resources.R
import com.flexath.core.utils.Dimens.LargePadding2
import com.flexath.core.utils.Dimens.MediumPadding4
import com.flexath.core.utils.Dimens.SmallPadding0
import com.flexath.core.utils.Dimens.SmallPadding3
import com.flexath.core.utils.Dimens.SmallPadding5
import com.flexath.findit.major.presentation.screens.common.StarRatingBar
import com.flexath.findit.major.presentation.screens.common.reviewCardList
import com.flexath.findit.major.presentation.screens.review.components.ReviewProductTopAppBar

@Composable
fun ReviewProductScreen(
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit
) {
    Column(
        modifier = modifier.background(color = com.flexath.resources.java.theme.colorBackground)
    ) {
        ReviewProductTopAppBar(
            title = stringResource(R.string.lbl_review_product),
            rating = 4.9f
        ) {
            onClickBackButton()
        }

        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(LargePadding2))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LargePadding2),
                ) {

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "4.9",
                                style = MaterialTheme.typography.headlineLarge,
                                color = com.flexath.resources.java.theme.textColorPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "/5",
                                style = MaterialTheme.typography.bodyMedium,
                                color = com.flexath.resources.java.theme.textColorPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Spacer(modifier = Modifier.height(SmallPadding3))

                        Text(
                            text = "86 Reviews",
                            style = MaterialTheme.typography.bodyMedium,
                            color = com.flexath.resources.java.theme.textColorPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.width(MediumPadding4))

                    VerticalDivider(
                        color = com.flexath.resources.java.theme.dividerColor,
                        thickness = SmallPadding0,
                        modifier = Modifier.height(80.dp)
                    )

                    Spacer(modifier = Modifier.width(SmallPadding5))

                    Column(
                        modifier = Modifier
                    ) {
                        for(i in 5 downTo 1) {
                            ReviewWithStarAndLinearProgressBar(i.toFloat())
                        }
                    }
                }

                Spacer(modifier = Modifier.height(LargePadding2))
            }

            reviewCardList(8)
        }
    }
}

@Composable
fun ReviewWithStarAndLinearProgressBar(
    numOfStar: Float,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        StarRatingBar(
            maxStars = 5,
            rating = numOfStar,
            onRatingChanged = {

            }
        )

        Spacer(modifier = Modifier.width(SmallPadding5))

        LinearProgressIndicator(
            progress = {
                0.7f
            },
            trackColor = com.flexath.resources.java.theme.dividerColor,
            color = com.flexath.resources.java.theme.starColor,
            strokeCap = StrokeCap.Round,
            modifier = Modifier.widthIn(max = 110.dp)
        )

        Spacer(modifier = Modifier.width(SmallPadding5))

        Text(
            text = "70",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = com.flexath.resources.java.theme.textColorPrimary
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ReviewProductScreenPreview() {
    ReviewProductScreen(
        modifier = Modifier.fillMaxSize(),
        onClickBackButton = {

        }
    )
}