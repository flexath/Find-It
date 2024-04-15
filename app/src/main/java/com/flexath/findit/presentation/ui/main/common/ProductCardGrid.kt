package com.flexath.findit.presentation.ui.main.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flexath.findit.presentation.ui.main.home.components.ProductCard
import com.flexath.findit.presentation.utils.Dimens

@Composable
fun ProductCardGrid(
    modifier: Modifier = Modifier,
    onClickProductCard: (id: Int) -> Unit,
    onClickVerticalDots: () -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .padding(horizontal = Dimens.LargePadding2)
    ) {
        items(count = 8) {
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