package com.flexath.findit.main.presentation.screens.common

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.presentation.screens.home.components.ProductCardList
import com.flexath.findit.main.presentation.screens.home.components.TitleSection

@Composable
fun ProductItemSection(
    context: Context,
    title: String,
    onClickSeeAll: () -> Unit,
    onClickProductCard: () -> Unit,
    onClickVerticalDots: () -> Unit,
    productItemList: List<ProductVO>?
) {
    Column {
        TitleSection(title = title) {
            onClickSeeAll()
        }

        Spacer(modifier = Modifier.height(Dimens.SmallPadding4))

        ProductCardList(
            productList = productItemList ?: emptyList(),
            modifier = Modifier
                .fillMaxWidth(),
            onClickProductCard = {
                onClickProductCard()
            },
            onClickVerticalDots = {
                onClickVerticalDots()
            },

        )
    }
}