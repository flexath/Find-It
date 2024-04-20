package com.flexath.findit.main.presentation.screens.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.core.utils.Dimens.SmallPadding4_1

@Composable
fun ProductCategoryList(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    categoryList: List<String>
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = SmallPadding4_1,
        )
    ) {
        if(categoryList.isNotEmpty()) {
            items(count = categoryList.size.coerceAtMost(10)) {index ->
                ProductCategory(
                    category = categoryList[index],
                    onClick = {
                        onClick(it)
                    }
                )
            }
        } else {
            items(count = 7) {
                ProductCategory(
                    category = "---",
                    onClick = {
                        onClick(it)
                    }
                )
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProductCategoryListPreview() {
    ProductCategoryList(
        modifier = Modifier.fillMaxWidth(),
        onClick = {

        },
        categoryList = emptyList()
    )
}