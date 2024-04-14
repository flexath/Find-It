package com.flexath.findit.presentation.ui.main.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flexath.findit.presentation.utils.Dimens

@Composable
fun ProductCardList(
    modifier: Modifier = Modifier,
    onClickProductCard: (id: Int) -> Unit,
    onClickVerticalDots: () -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = Dimens.MediumPadding3,
        )
    ) {
        items(count = 5) {
            ProductCard(
                onClickProductCard = {
                    onClickProductCard(it)
                },
                onClickVerticalDots = {
                    onClickVerticalDots()
                }
            )
        }
    }
}