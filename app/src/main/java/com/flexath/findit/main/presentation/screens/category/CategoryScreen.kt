package com.flexath.findit.main.presentation.screens.category

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens.ExtraLargePadding5_2x
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.main.presentation.screens.common.CustomOutlinedButton
import com.flexath.findit.main.presentation.screens.common.DetailTopAppBarWithOneAction
import com.flexath.findit.main.presentation.screens.common.ProductCardGridList
import com.flexath.findit.core.presentation.common.TextFieldBar
import com.flexath.findit.main.presentation.screens.common.bottom_sheet.FilterAndSortingContentBottomSheet
import com.flexath.findit.main.presentation.view_model.ProductViewModel
import com.flexath.findit.theme.colorBackground
import com.flexath.findit.theme.textColorPrimary

@Composable
fun CategoryScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickProductCard: (Int) -> Unit,
    categoryName: String,
    productViewModel: ProductViewModel,
) {
    LaunchedEffect(key1 = Unit) {
        productViewModel.fetchAllProductsOfCategory(categoryName)
    }
    val productListState = productViewModel.productListOfCategoryState.collectAsStateWithLifecycle()

    var query by remember {
        mutableStateOf("")
    }

    var filterSortingBottomSheetShow by rememberSaveable {
        mutableStateOf(false)
    }

    FilterAndSortingContentBottomSheet(
        bottomSheetShow = filterSortingBottomSheetShow,
        onSheetShowChange = {
            filterSortingBottomSheetShow = it
        },
        onClickApplyButton = {
            filterSortingBottomSheetShow = false
        }
    )

    Box(
        modifier = modifier.background(colorBackground)
    ) {
        LazyColumn{

            item {
                DetailTopAppBarWithOneAction(
                    title = stringResource(R.string.lbl_category),
                    actionIcon = R.drawable.ic_cart,
                    onClickBackButton = {
                        onClickBackButton()
                    },
                    onClickActionButton = {
                        // Share news article code
                    }
                )

                Spacer(modifier = Modifier.height(LargePadding2))

                Text(
                    text = categoryName,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = textColorPrimary,
                    modifier = Modifier.padding(horizontal = LargePadding2)
                )

                Spacer(modifier = Modifier.height(LargePadding2))

                TextFieldBar(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LargePadding2),
                    isEnabled = true,
                    query = query,
                    onQueryChange = {
                        query = it
                    },
                    isClickable = false,
                    onClickSearchBar = {

                    },
                    onSearch = {

                    }
                )

                Spacer(modifier = Modifier.height(LargePadding2))
            }

            item {

                ProductCardGridList(
                    modifier = Modifier.fillMaxWidth(),
                    productList = productListState.value.productList,
                    onClickVerticalDots = {

                    },
                    onClickProductCard = {
                        onClickProductCard(it)
                    }
                )

                Spacer(modifier = Modifier.height(ExtraLargePadding5_2x))
            }
        }

        CustomOutlinedButton(
            text = stringResource(R.string.lbl_filter_sorting),
            modifier = Modifier
                .fillMaxWidth()
                .padding(LargePadding2)
                .align(Alignment.BottomCenter)
        ) {
            filterSortingBottomSheetShow = true
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoryScreenPreview() {
    CategoryScreen(
        context = LocalContext.current,
        onClickBackButton = {},
        onClickProductCard = {

        },
        categoryName = "",
        productViewModel = hiltViewModel()
    )

}