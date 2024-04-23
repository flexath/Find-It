package com.flexath.findit.news.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.main.presentation.screens.common.DetailTopAppBar
import com.flexath.findit.main.presentation.screens.common.SearchBar
import com.flexath.findit.main.presentation.screens.common.articleCardList
import com.flexath.findit.main.presentation.screens.common.articleCardPagingList
import com.flexath.findit.news.domain.model.ArticleVO
import kotlinx.coroutines.flow.flowOf

@Composable
fun NewsListScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickArticleCard: (ArticleVO) -> Unit,
    articleList: LazyPagingItems<ArticleVO>,
) {
    var query by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
    ) {
        DetailTopAppBar(
            title = stringResource(R.string.lbl_news)
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
                    onClickSearchBar = {},
                    onQueryChange = {
                        query = it
                    },
                    onSearch = {

                    }
                )
            }

            articleCardPagingList(
                context = context,
                articleList = articleList,
                onClick = { article ->
                    onClickArticleCard(article)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewsListScreenPreview() {
    NewsListScreen(
        context = LocalContext.current,
        onClickBackButton = {

        },
        onClickArticleCard = {

        },
        articleList = flowOf(PagingData.from(listOf<ArticleVO>())).collectAsLazyPagingItems()
    )
}