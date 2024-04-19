package com.flexath.findit.main.presentation.screens.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.theme.textColorPrimary

@Composable
fun RatingTextWithIcon(
    modifier: Modifier = Modifier,
    rating: Float
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
            contentDescription = "Rating Star",
            tint = Color(0xFFFFC120),
            modifier = Modifier.size(Dimens.MediumPadding3)
        )

        Text(
            text = rating.toString(),
            style = MaterialTheme.typography.titleSmall,
            color = textColorPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = Dimens.SmallPadding1)
        )
    }
}