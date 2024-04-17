package com.flexath.findit.presentation.ui.main.home.components

import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.flexath.findit.R
import com.flexath.findit.presentation.ui.main.common.BottomSheetUtil

@Composable
fun CategoryContentBottomSheet(
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    onClick: () -> Unit,
) {
    BottomSheetUtil(
        title = stringResource(R.string.lbl_all_categories),
        bottomSheetShow = bottomSheetShow,
        onSheetShowChange = {
            onSheetShowChange(it)
        },
        content = {
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