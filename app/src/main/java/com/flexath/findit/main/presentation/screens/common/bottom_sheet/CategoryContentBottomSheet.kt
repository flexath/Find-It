package com.flexath.findit.main.presentation.screens.common.bottom_sheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.main.presentation.screens.common.BottomSheetUtil
import com.flexath.findit.main.presentation.screens.home.components.ProductCategory

@Composable
fun CategoryContentBottomSheet(
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    onClick: (String) -> Unit,
    categoryList: List<String>,
) {
    BottomSheetUtil(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        title = stringResource(R.string.lbl_all_categories),
        bottomSheetShow = bottomSheetShow,
        onSheetShowChange = {
            onSheetShowChange(it)
        },
        content = {
            Spacer(modifier = Modifier.height(Dimens.MediumPadding5))

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(4)
            ) {
                items(count = categoryList.size) {index ->
                    ProductCategory(
                        onClick = {
                            onClick(it)
                        },
                        category = categoryList[index]
                    )
                }
            }
        }
    )
}