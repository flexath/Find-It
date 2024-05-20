package com.flexath.findit.news.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.main.presentation.events.SearchEvent
import com.flexath.findit.main.presentation.screens.common.DetailTopAppBar
import com.flexath.findit.core.presentation.common.TextFieldBar
import com.flexath.findit.main.presentation.screens.common.articleCardPagingList
import com.flexath.findit.news.domain.model.ArticleVO
import com.flexath.findit.news.presentation.view_models.NewsViewModel

@Composable
fun NewsListScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickArticleCard: (ArticleVO) -> Unit,
    event: (SearchEvent) -> Unit,
    newsViewModel: NewsViewModel
) {
    val newsSearchState = newsViewModel.newsSearchState.value

    val articles = newsViewModel.newsSearchState.value.articles?.collectAsLazyPagingItems()

//    LaunchedEffect(key1 = newsSearchState.query.isEmpty()) {
//        newsViewModel.fetchAllNews()
//    }

    Column(
        modifier = modifier
    ) {
        DetailTopAppBar(
            title = stringResource(R.string.lbl_news),
            onClickBackButton = {
                onClickBackButton()
            }
        )

        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(Dimens.LargePadding2))

                TextFieldBar(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimens.LargePadding2),
                    isEnabled = true,
                    query = newsSearchState.query,
                    onQueryChange = { query ->
                        event(SearchEvent.UpdateQuery(query))
                    },
                    isClickable = false,
                    onClickSearchBar = {},
                    onSearch = { _ ->
                        event(SearchEvent.Search)
                    }
                )
            }

            if (articles != null) {
                articleCardPagingList(
                    context = context,
                    articleList = articles,
                    onClick = { article ->
                        onClickArticleCard(article)
                    }
                )
            }
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
        newsViewModel = hiltViewModel(),
        event = {

        }
    )
}