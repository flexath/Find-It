package com.flexath.findit.presentation.ui.main.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import com.flexath.findit.presentation.theme.dividerColor
import com.flexath.findit.presentation.utils.Dimens.LargePadding2
import com.flexath.findit.presentation.utils.Dimens.SmallPadding0


fun LazyListScope.articleCardList(
    onClick: (Int) -> Unit
) {
    items(count = 3) {index ->
        ArticleCard {
            onClick(it)
        }

        if(index < 2) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = LargePadding2),
                thickness = SmallPadding0,
                color = dividerColor
            )
        }
    }
}