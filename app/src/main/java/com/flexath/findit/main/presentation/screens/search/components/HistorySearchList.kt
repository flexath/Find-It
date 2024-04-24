package com.flexath.findit.main.presentation.screens.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.MediumPadding2
import com.flexath.findit.main.domain.model.HistoryVO

fun LazyListScope.historySearchList(historyList: List<HistoryVO>) {
    items(count = historyList.size) {index ->
        HistorySearch(
            modifier = Modifier.fillMaxWidth().padding(horizontal = LargePadding2, vertical = MediumPadding2),
            history = historyList[index]
        )
    }
}