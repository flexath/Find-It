package com.flexath.findit.major.presentation.screens.search.components

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
import com.flexath.resources.R
import com.flexath.core.utils.Dimens

@Composable
fun HistorySearch(
    modifier: Modifier = Modifier,
    history: com.flexath.findit.major.domain.model.HistoryVO,
    onClickDeleteButton: (Int) -> Unit
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
                color = com.flexath.resources.java.theme.textColorPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = Dimens.SmallPadding5)
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "Close Button",
            modifier = Modifier.clickable {
                onClickDeleteButton(history.id)
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HistorySearchPreview() {
    HistorySearch(
        modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.LargePadding2),
        history = com.flexath.findit.major.domain.model.HistoryVO(
            query = ""
        ),
        onClickDeleteButton = {

        }
    )
}