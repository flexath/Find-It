package com.flexath.findit.presentation.ui.main.common

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.presentation.ui.main.home.components.ProductCard
import com.flexath.findit.presentation.utils.Dimens
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductCardGrid(
    modifier: Modifier = Modifier,
    onClickProductCard: (id: Int) -> Unit,
    onClickVerticalDots: () -> Unit
) {
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
        modifier = modifier
            .padding(horizontal = Dimens.MediumPadding3)
    ) {
        (1..5).forEachIndexed { index, num ->
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProductCardGridPreview() {
    ProductCardGrid(
        modifier = Modifier.fillMaxWidth(),
        onClickProductCard = {

        },
        onClickVerticalDots = {

        }
    )
}