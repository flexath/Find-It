package com.flexath.findit.presentation.ui.main.common.bottom_sheet

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
import com.flexath.findit.presentation.ui.main.common.BottomSheetUtil
import com.flexath.findit.presentation.ui.main.home.components.ProductCategory
import com.flexath.findit.presentation.utils.Dimens

@Composable
fun CategoryContentBottomSheet(
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    onClick: () -> Unit,
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
                items(count = 8) {
                    ProductCategory(
                        onClick = {
                            onClick()
                        }
                    )
                }
            }
        }
    )
}