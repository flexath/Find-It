package com.flexath.findit.main.presentation.screens.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens.ExtraLargePadding4
import com.flexath.findit.core.utils.Dimens.ExtraLargePadding5
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.MediumPadding1
import com.flexath.findit.core.utils.Dimens.MediumPadding3
import com.flexath.findit.core.utils.Dimens.SmallPadding4
import com.flexath.findit.theme.textColorPrimary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductCategory(
    category: String,
    onClick: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            start = MediumPadding3,
            end = MediumPadding3,
            top = MediumPadding3,
            bottom = SmallPadding4
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(ExtraLargePadding4)
                .aspectRatio(1f / 1f)
                .clip(RoundedCornerShape(MediumPadding3))
                .background(
                    color = Color(0xFFFFECE8),
                    shape = RoundedCornerShape(MediumPadding3)
                )
                .clickable {
                    onClick("Foods")
                }
        ) {
            Spacer(modifier = Modifier.height(MediumPadding1))

            Image(
                painter = painterResource(id = R.drawable.img_shopping),
                contentDescription = "Category Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = MediumPadding1)
                    .size(LargePadding2)
            )
        }

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            color = textColorPrimary,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            modifier = Modifier.width(ExtraLargePadding5).basicMarquee()

        )

        Spacer(modifier = Modifier.height(SmallPadding4))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductCategoryPreview() {
    ProductCategory(
        category = "SmartPhones",
        onClick = {}
    )
}