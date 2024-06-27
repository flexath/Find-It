package com.flexath.news.presentation.screens.components

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.flexath.core.utils.Dimens.LargePadding2
import com.flexath.core.utils.Dimens.SmallPadding0
import com.flexath.news.domain.model.ArticleVO

fun LazyListScope.articleCardList(
    context: Context,
    onClick: (ArticleVO) -> Unit,
    articleList: List<ArticleVO>
) {
    items(count = articleList.size.coerceAtMost(3)) {index ->
        ArticleCard(
            context = context,
            article = articleList[index],
            onClick = { article ->
                onClick(article)
            }
        )

        if(index < articleList.size.coerceAtMost(3)-1) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = LargePadding2),
                thickness = SmallPadding0,
                color = com.flexath.resources.java.theme.dividerColor
            )
        }
    }
}

fun LazyListScope.articleCardPagingList(
    context: Context,
    onClick: (ArticleVO) -> Unit,
    articleList: LazyPagingItems<ArticleVO>
) {
    items(count = articleList.itemCount) {index ->
        articleList[index]?.let {
            ArticleCard(
                context = context,
                article = it,
                onClick = { article ->
                    onClick(article)
                }
            )
        }

        if(index < articleList.itemCount-1) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = LargePadding2),
                thickness = SmallPadding0,
                color = com.flexath.resources.java.theme.dividerColor
            )
        }
    }
}