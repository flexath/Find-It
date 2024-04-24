package com.flexath.findit.main.presentation.screens.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.main.domain.model.HistoryVO
import com.flexath.findit.theme.textColorPrimary

@Composable
fun HistorySearch(
    modifier: Modifier = Modifier,
    history: HistoryVO
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Row {
            Icon(painter = painterResource(id = R.drawable.ic_clock), contentDescription = "Close Button")

            Text(
                text = history.query.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                color = textColorPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = Dimens.SmallPadding5)
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "Close Button",
            modifier = Modifier.clickable {

            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HistorySearchPreview() {
    HistorySearch(
        modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.LargePadding2),
        history = HistoryVO(
            query = ""
        )
    )
}