package com.flexath.findit.major.presentation.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.resources.R
import com.flexath.core.utils.Dimens.LargePadding2

@Composable
fun TitleSection(
    title: String,
    onClickSeeAll: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LargePadding2
            )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Medium,
            ),
            color = com.flexath.resources.java.theme.textColorPrimary
        )

        Text(
            text = stringResource(R.string.lbl_see_all),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = com.flexath.resources.java.theme.colorPrimary,
            modifier = Modifier.clickable {
                onClickSeeAll()
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TitleSectionPreview() {
    TitleSection("All Products"){

    }
}