package com.flexath.findit.presentation.ui.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.presentation.theme.textColorPrimary
import com.flexath.findit.presentation.theme.textColorSecondary
import com.flexath.findit.presentation.utils.Dimens
import com.flexath.findit.presentation.utils.Dimens.ArticleCardWidth
import com.flexath.findit.presentation.utils.Dimens.LargePadding2
import com.flexath.findit.presentation.utils.Dimens.SmallPadding4
import com.flexath.findit.presentation.utils.Dimens.SmallPadding5

@Composable
fun ArticleCard(
    onClick: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(LargePadding2)
            .clickable {
                onClick(1)
            }
    ) {
        Column(
            modifier = Modifier.weight(3f).padding(end = SmallPadding4)
        ) {
            Text(
                text = "Philosophy That Addresses Topics Such As Goodness",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = textColorPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(SmallPadding5))

            Text(
                text = "Agar tetap kinclong, bodi motor ten",
                style = MaterialTheme.typography.bodySmall,
                color = textColorPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(SmallPadding5))

            Text(
                text = "13 Jan 2021",
                style = MaterialTheme.typography.bodySmall,
                color = textColorSecondary,
                maxLines = 1
            )
        }

        Image(
            painter = painterResource(id = R.drawable.dummy_article),
            contentDescription = "Article Cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f/1f)
                .size(ArticleCardWidth)
                .clip(RoundedCornerShape(Dimens.MediumPadding3))
                .weight(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleCardPreview() {
    ArticleCard {

    }
}