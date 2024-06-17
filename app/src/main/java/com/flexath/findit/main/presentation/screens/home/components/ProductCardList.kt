package com.flexath.findit.main.presentation.screens.home.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.main.domain.model.ProductVO

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProductCardList(
    modifier: Modifier = Modifier,
    onClickProductCard: (Int) -> Unit,
    onClickVerticalDots: () -> Unit,
    productList: List<ProductVO>,
    animatedVisibilityScope: AnimatedVisibilityScope
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
                    onClickProductCard = { id ->
                        onClickProductCard(id)
                    },
                    onClickVerticalDots = {
                        onClickVerticalDots()
                    },
                    product = productList[index],
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
        } else {
            items(count = 3) {_ ->
                ProductCard(
                    product = null,
                    animatedVisibilityScope = animatedVisibilityScope,
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