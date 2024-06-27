package com.flexath.findit.major.presentation.screens.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.flexath.core.utils.Dimens.LargePadding2

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