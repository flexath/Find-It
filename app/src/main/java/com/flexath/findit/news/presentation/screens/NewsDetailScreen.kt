package com.flexath.findit.news.presentation.screens

import android.content.Context
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.flexath.findit.R
import com.flexath.findit.core.utils.ContextUtils.shareLink
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.main.presentation.screens.common.CustomOutlinedButton
import com.flexath.findit.main.presentation.screens.common.DetailTopAppBarWithOneAction
import com.flexath.findit.main.presentation.screens.common.articleCardList
import com.flexath.findit.news.domain.model.ArticleVO
import com.flexath.findit.news.presentation.view_models.NewsViewModel
import com.flexath.findit.theme.textColorPrimary
import com.flexath.findit.theme.textColorSecondary

@Composable
fun NewsDetailScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickArticleCard: (ArticleVO) -> Unit,
    onClickSeeAllNewsButton: () -> Unit,
    article: ArticleVO,
    newsViewModel: NewsViewModel
) {
    LaunchedEffect(key1 = Unit) {
        newsViewModel.fetchNewsForHomeScreen()
    }

    val articleList = newsViewModel.articleListHomeState.value.articleList

    Column(
        modifier = modifier
    ) {
        DetailTopAppBarWithOneAction(
            title = stringResource(R.string.lbl_detail_news),
            actionIcon = R.drawable.ic_share,
            onClickBackButton = {
                onClickBackButton()
            },
            onClickActionButton = {
                context.shareLink(article.url)
            }
        )

        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(LargePadding2))

                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
                    contentDescription = "Banner Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = LargePadding2)
                        .aspectRatio(13f / 8f)
                        .clip(RoundedCornerShape(Dimens.MediumPadding3))
                )

                Spacer(modifier = Modifier.height(LargePadding2))

                Text(
                    text = article.title.orEmpty(),
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = LargePadding2)
                )

                Spacer(modifier = Modifier.height(Dimens.SmallPadding5))

                Text(
                    text = article.formatPublishedAtTime(),
                    style = MaterialTheme.typography.bodySmall,
                    color = textColorSecondary,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = LargePadding2)
                )

                Spacer(modifier = Modifier.height(LargePadding2))

                Text(
                    text = article.content ?: "",
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

            articleCardList(
                context = context,
                articleList = articleList,
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
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun NewsDetailScreenPreview() {
    NewsDetailScreen(
        context = LocalContext.current,
        modifier = Modifier.fillMaxSize(),
        onClickBackButton = {

        },
        onClickArticleCard = {

        },
        onClickSeeAllNewsButton = {

        },
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
        newsViewModel = hiltViewModel()
    )
}