package com.flexath.news.presentation.screens.components

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.flexath.core.utils.Dimens
import com.flexath.core.utils.Dimens.ArticleCardWidth
import com.flexath.core.utils.Dimens.LargePadding2
import com.flexath.core.utils.Dimens.SmallPadding4
import com.flexath.core.utils.Dimens.SmallPadding5
import com.flexath.news.domain.model.ArticleVO

@Composable
fun ArticleCard(
    context: Context,
    onClick: (ArticleVO) -> Unit,
    article: ArticleVO
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(article)
            }
            .padding(LargePadding2)
    ) {
        Column(
            modifier = Modifier.weight(3f).padding(end = SmallPadding4)
        ) {
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = com.flexath.resources.java.theme.textColorPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(SmallPadding5))

            Text(
                text = article.description ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = com.flexath.resources.java.theme.textColorPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(SmallPadding5))

            Text(
                text = article.formatPublishedAtTime(),
                style = MaterialTheme.typography.bodySmall,
                color = com.flexath.resources.java.theme.textColorSecondary,
                maxLines = 1
            )
        }

        AsyncImage(
            model = ImageRequest.Builder(context = context).data(article.urlToImage ?: "").build(),
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
    ArticleCard(
        context = LocalContext.current,
        article = ArticleVO(
            "Aung Thiha",
            content = "Philosophy That Addresses Topics Such As Goodness",
            description = "Agar tetap kinclong, bodi motor ten",
            publishedAt = "13 Jan 2021",
            source = null,
            title = "Philosophy That Addresses Topics Such As Goodness",
            url = "",
            urlToImage = ""
        ),
        onClick = {}
    )
}