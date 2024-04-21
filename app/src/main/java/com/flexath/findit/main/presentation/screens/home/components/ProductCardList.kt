package com.flexath.findit.main.presentation.screens.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.main.domain.model.ProductVO

@Composable
fun ProductCardList(
    modifier: Modifier = Modifier,
    onClickProductCard: (Int) -> Unit,
    onClickVerticalDots: () -> Unit,
    productList: List<ProductVO>
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = Dimens.MediumPadding3,
        )
    ) {
        if(productList.isNotEmpty()) {
            items(productList.size.coerceAtMost(5)) {index ->
                ProductCard(
                    product = productList[index],
                    onClickProductCard = {
                        onClickProductCard(it)
                    },
                    onClickVerticalDots = {
                        onClickVerticalDots()
                    }
                )
            }
        } else {
            items(count = 3) {_ ->
                ProductCard(
                    product = ProductVO(
                        title = "",
                        price = 1,
                        rating = 0.0,
                        stock = 1,
                        brand = "",
                        thumbnail = "",
                        images = emptyList()
                    ),
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
}