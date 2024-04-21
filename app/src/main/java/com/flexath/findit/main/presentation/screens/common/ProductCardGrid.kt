package com.flexath.findit.main.presentation.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.presentation.screens.home.components.ProductCardGrid


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductCardGridList(
    modifier: Modifier = Modifier,
    onClickProductCard: (id: Int) -> Unit,
    onClickVerticalDots: () -> Unit
) {
    FlowColumn(
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .padding(horizontal = Dimens.MediumPadding3)
    ) {
//        (1..5).forEachIndexed { index, num ->
//            ProductCardGrid(
//                onClickProductCard = {
//                    onClickProductCard(it)
//                },
//                onClickVerticalDots = {
//                    onClickVerticalDots()
//                },
//                product = ProductVO(
//                    title = "",
//                    price = 1,
//                    rating = 0.0,
//                    stock = 1,
//                    brand = "",
//                    thumbnail = "",
//                    images = emptyList()
//                )
//            )
//        }

        SimpleGridView(
            modifier = Modifier.fillMaxWidth(),
            columns = 2,
            countOfItems = 6,
        ) { index ->
//            listOfItems.getOrNull(index)?.let {
//
//            }

            ProductCardGrid(
                onClickProductCard = {
                    onClickProductCard(it)
                },
                onClickVerticalDots = {
                    onClickVerticalDots()
                },
                product = ProductVO(
                    title = "",
                    price = 1,
                    rating = 0.0,
                    stock = 1,
                    brand = "",
                    thumbnail = "",
                    images = emptyList()
                )
            )
        }
    }
}

@Composable
fun SimpleGridView(
    modifier: Modifier = Modifier,
    columns: Int,
    countOfItems: Int,
    content: @Composable() (index: Int) -> Unit
) {
    val columnAndRowItems = (0..countOfItems).chunked(columns)

    Column(modifier = modifier) {
        columnAndRowItems.forEach { rowItems ->
            Row(modifier = Modifier.fillMaxWidth()) {
                rowItems.forEach { index ->
                    Box(modifier = Modifier.weight(1f)) {
                        content(index)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProductCardGridPreview() {
    ProductCardGridList(
        modifier = Modifier.fillMaxWidth(),
        onClickProductCard = {

        },
        onClickVerticalDots = {

        }
    )
}