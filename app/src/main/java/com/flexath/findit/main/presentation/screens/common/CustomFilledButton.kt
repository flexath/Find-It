package com.flexath.findit.main.presentation.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.theme.alertColor
import com.flexath.findit.theme.colorBackground
import com.flexath.findit.theme.colorPrimary
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.core.utils.Dimens.SmallPadding5
import com.flexath.findit.theme.hintColor

@Composable
fun CustomFilledButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(corner = CornerSize(SmallPadding5)),
        contentPadding = PaddingValues(vertical = Dimens.MediumPadding3),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorPrimary,
            disabledContainerColor = hintColor,
        ),
        enabled = isEnabled,
        modifier = modifier
            .clip(RoundedCornerShape(SmallPadding5))
            .background(color = colorBackground)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = colorBackground,
            maxLines = 1
        )
    }
}

@Composable
fun CustomFilledButtonWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(corner = CornerSize(SmallPadding5)),
        colors = ButtonDefaults.buttonColors(
            containerColor = alertColor
        ),
        contentPadding = PaddingValues(
            vertical = Dimens.MediumPadding3,
            horizontal = MediumPadding5
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
            color = colorBackground,
            maxLines = 1,
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = R.drawable.ic_heart_white),
            contentDescription = "Added Button",
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomFilledButtonPreview() {

    Column {
        CustomFilledButton(
            text = "Add to Card",
            modifier = Modifier.fillMaxWidth(),
            onClick = {

            }
        )

        CustomFilledButtonWithIcon(
            text = "Added",
            modifier = Modifier.fillMaxWidth(),
            onClick = {

            }
        )
    }


}