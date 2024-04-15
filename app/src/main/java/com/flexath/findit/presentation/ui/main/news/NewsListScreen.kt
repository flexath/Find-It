package com.flexath.findit.presentation.ui.main.news

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.presentation.ui.main.common.DetailTopAppBar
import com.flexath.findit.presentation.ui.main.common.SearchBar
import com.flexath.findit.presentation.ui.main.home.components.articleCardList
import com.flexath.findit.presentation.utils.Dimens

@Composable
fun NewsListScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickArticleCard: () -> Unit,
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
                    isEnabled = true
                ) {
                    query = it
                }
            }

            articleCardList {
                onClickArticleCard()
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

        }
    )
}