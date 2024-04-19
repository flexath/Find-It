package com.flexath.findit.main.presentation.screens.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.flexath.findit.core.utils.Dimens.LargePadding2

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