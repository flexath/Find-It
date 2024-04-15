package com.flexath.findit.presentation.ui.main.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.flexath.findit.R
import com.flexath.findit.presentation.utils.Dimens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerSection(
    pagerState: PagerState
) {
    HorizontalPager(
        state = pagerState,
        pageSpacing = Dimens.MediumPadding2,
        contentPadding = PaddingValues(
            horizontal = Dimens.LargePadding2
        ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(21f / 10f)
                .clip(RoundedCornerShape(Dimens.MediumPadding3))
        )
    }
}