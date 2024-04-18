package com.flexath.findit.presentation.ui.main.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.flexath.findit.presentation.utils.Dimens.LargePadding2

fun LazyListScope.reviewCardList(
    count: Int = 3,
) {
    items(count = count) {
        ReviewCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LargePadding2)
        )
    }
}