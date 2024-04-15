package com.flexath.findit.presentation.ui.main.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.presentation.theme.textColorPrimary
import com.flexath.findit.presentation.utils.Dimens.LargePadding2
import com.flexath.findit.presentation.utils.Dimens.MediumPadding1
import com.flexath.findit.presentation.utils.Dimens.MediumPadding3
import com.flexath.findit.presentation.utils.Dimens.SmallPadding0
import com.flexath.findit.presentation.utils.Dimens.SmallPadding5

@Composable
fun CustomOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = {
            onClick()
        },
        border = BorderStroke(SmallPadding0, textColorPrimary),
        shape = RoundedCornerShape(corner = CornerSize(SmallPadding5)),
        modifier = modifier
            .clip(RoundedCornerShape(SmallPadding5))
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = textColorPrimary,
            maxLines = 1,
            modifier = Modifier.padding(vertical = MediumPadding1)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SeeAllNewsButtonPreview() {
    CustomOutlinedButton(
        text  = "Sell All News",

    ) {

    }
}