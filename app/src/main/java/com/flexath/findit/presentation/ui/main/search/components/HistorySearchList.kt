package com.flexath.findit.presentation.ui.main.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flexath.findit.presentation.utils.Dimens.LargePadding2
import com.flexath.findit.presentation.utils.Dimens.MediumPadding2
import com.flexath.findit.presentation.utils.Dimens.SmallPadding2

fun LazyListScope.historySearchList() {
    items(count = 15) {index ->
        HistorySearch(
            modifier = Modifier.fillMaxWidth().padding(horizontal = LargePadding2, vertical = MediumPadding2)
        )
    }
}