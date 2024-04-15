package com.flexath.findit.presentation.ui.main.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.presentation.theme.colorBackground
import com.flexath.findit.presentation.theme.textColorPrimary
import com.flexath.findit.presentation.utils.Dimens.MediumPadding3
import com.flexath.findit.presentation.utils.Dimens.SmallPadding0
import com.flexath.findit.presentation.utils.Dimens.SmallPadding5

@Composable
fun CustomOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
        },
        border = BorderStroke(SmallPadding0, textColorPrimary),
        shape = RoundedCornerShape(corner = CornerSize(SmallPadding5)),
        contentPadding = PaddingValues(vertical = MediumPadding3),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorBackground
        ),
        modifier = modifier
            .clip(RoundedCornerShape(SmallPadding5))
            .background(color = colorBackground)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = textColorPrimary,
            maxLines = 1,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SeeAllNewsButtonPreview() {
    CustomOutlinedButton(
        text  = "Sell All News",
        modifier = Modifier.fillMaxWidth()
    ) {

    }
}