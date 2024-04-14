package com.flexath.findit.presentation.ui.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.presentation.utils.Dimens.ExtraLargePadding4
import com.flexath.findit.presentation.utils.Dimens.MediumPadding1
import com.flexath.findit.presentation.utils.Dimens.MediumPadding3
import com.flexath.findit.presentation.utils.Dimens.MediumPadding5
import com.flexath.findit.presentation.utils.Dimens.SmallPadding4

@Composable
fun ProductCategory(
    onClick: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            start = MediumPadding3,
            end = MediumPadding3,
            top = MediumPadding3,
            bottom = SmallPadding4,
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
                painter = painterResource(id = R.drawable.dummy_ic_vegetable_2),
                contentDescription = "Category Image",
                modifier = Modifier
                    .padding(horizontal = MediumPadding1)
            )
        }

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = "Foods",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(SmallPadding4))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductCategoryPreview() {
    ProductCategory{

    }
}