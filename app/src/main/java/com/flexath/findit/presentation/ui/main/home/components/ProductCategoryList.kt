package com.flexath.findit.presentation.ui.main.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.presentation.utils.Dimens.MediumPadding3
import com.flexath.findit.presentation.utils.Dimens.SmallPadding4_1

@Composable
fun ProductCategoryList(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = SmallPadding4_1,
        )
    ) {
        items(count = 8) {
            ProductCategory(
                onClick = {
                    onClick(it)
                }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProductCategoryListPreview() {
    ProductCategoryList(
        Modifier
    ) {

    }
}