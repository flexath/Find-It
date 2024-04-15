package com.flexath.findit.presentation.ui.main.search

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.presentation.theme.textColorPrimary
import com.flexath.findit.presentation.ui.main.common.DetailTopAppBar
import com.flexath.findit.presentation.ui.main.common.ProductItemSection
import com.flexath.findit.presentation.ui.main.common.SearchBar
import com.flexath.findit.presentation.ui.main.search.components.historySearchList
import com.flexath.findit.presentation.utils.Dimens.LargePadding2

@Composable
fun SearchScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickProductCard: () -> Unit
) {
    var query by remember {
        mutableStateOf("")
    }

    var hasSearch by remember {
        mutableStateOf(false)
    }

    hasSearch = remember {
        derivedStateOf {
            query.isNotEmpty()
        }.value
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ){
            DetailTopAppBar(
                title = stringResource(R.string.lbl_search)
            ) {
                onClickBackButton()
            }

            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(LargePadding2))

                    SearchBar(
                        context = context,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LargePadding2),
                        query = query,
                        isEnabled = true,
                        isClickable = false,
                        onClickSearchBar = {

                        },
                        onQueryChange = {
                            query = it
                        },
                        onSearch = {
                            hasSearch = true
                        }
                    )

                    if(!hasSearch) {
                        Column {
                            Spacer(modifier = Modifier.height(LargePadding2))

                            Text(
                                text = stringResource(R.string.lbl_recent_search),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = textColorPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = LargePadding2)
                            )
                        }
                    }
                }

                if(!hasSearch) {
                    historySearchList()
                }

            }
        }

        if(query.isEmpty()) {
            Column {
                Spacer(modifier = Modifier.height(LargePadding2))

                ProductItemSection(
                    context = context,
                    title = stringResource(id = R.string.lbl_featured_product),
                    onClickProductCard = {
                        onClickProductCard()
                    }
                )

                Spacer(modifier = Modifier.height(LargePadding2))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        context = LocalContext.current,
        onClickProductCard = {

        },
        onClickBackButton = {

        }
    )
}