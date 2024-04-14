package com.flexath.findit.presentation.ui.main.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.presentation.ui.main.common.SearchBar
import com.flexath.findit.presentation.ui.main.home.components.BannerSection
import com.flexath.findit.presentation.ui.main.home.components.ProductCategoryList
import com.flexath.findit.presentation.ui.main.home.components.TitleSection
import com.flexath.findit.presentation.utils.Dimens.LargePadding2
import com.flexath.findit.presentation.utils.Dimens.LargePadding5
import com.flexath.findit.presentation.utils.Dimens.MediumPadding2
import com.flexath.findit.presentation.utils.Dimens.MediumPadding3
import com.flexath.findit.presentation.utils.Dimens.SmallPadding4_1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {

    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState { 3 }
    val context = LocalContext.current

    var query by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(LargePadding2))

        // Need to connect query with ViewModel
        SearchBar(
            context = context,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LargePadding2),
            query = query
        ) {
            query = it
        }

        Spacer(modifier = Modifier.height(LargePadding2))

        BannerSection(pagerState = pagerState)

        Spacer(modifier = Modifier.height(LargePadding5))

        TitleSection(title = stringResource(R.string.lbl_categories)) {
            // add SeeAll text button clicking codes
            Toast.makeText(context, "SeeAll is clicked", Toast.LENGTH_SHORT).show()
        }

        ProductCategoryList(
            modifier = Modifier
                .fillMaxWidth()
        ) {

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}