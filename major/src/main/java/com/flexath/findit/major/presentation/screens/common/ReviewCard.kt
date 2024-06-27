package com.flexath.findit.major.presentation.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.resources.R
import com.flexath.core.utils.Dimens
import com.flexath.core.utils.Dimens.MediumPadding3
import com.flexath.core.utils.Dimens.MediumPadding5
import com.flexath.core.utils.Dimens.SmallPadding2
import com.flexath.core.utils.Dimens.SmallPadding5

@Composable
fun ReviewCard(
    modifier: Modifier = Modifier
) {
    var rating by remember { mutableFloatStateOf(1f) }

    Row(
        modifier = modifier
            .padding(vertical = MediumPadding5)
    ) {
        Image(
            painter = painterResource(id = R.drawable.dummy_review_writer),
            contentDescription = "Seller Cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(Dimens.SellerProfileWidth)
                .aspectRatio(1f / 1f)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier.padding(start = MediumPadding3)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Yelena Belova",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = com.flexath.resources.java.theme.textColorPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(end = MediumPadding3)
                        .weight(1.8f)
                )

                Text(
                    text = "23 minutes ago",
                    style = MaterialTheme.typography.bodyMedium,
                    color = com.flexath.resources.java.theme.textColorSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1.2f)
                )
            }

            Spacer(modifier = Modifier.height(SmallPadding2))

            StarRatingBar(
                maxStars = 5,
                rating = rating,
                onRatingChanged = {
                    rating = it
                }
            )

            Spacer(modifier = Modifier.height(SmallPadding5))

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                style = MaterialTheme.typography.bodyMedium,
                color = com.flexath.resources.java.theme.textColorPrimary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ReviewCardPreview() {
    ReviewCard(
        modifier = Modifier.fillMaxWidth()
    )
}