package com.flexath.findit.major.presentation.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.core.utils.Dimens
import com.flexath.core.utils.Dimens.LargePadding2
import com.flexath.core.utils.Dimens.MediumPadding5
import com.flexath.resources.R

@Composable
fun BottomSheetUtil(
    modifier: Modifier = Modifier,
    title: String,
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    isVisibleHorizontalDivider: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    if (bottomSheetShow) {
        CustomBottomSheet(
            modifier = modifier,
            title = title,
            onClickCloseButton = {
                onSheetShowChange(false)
            },
            onDismissRequest = {
                onSheetShowChange(false)
            },
            isVisibleHorizontalDivider = isVisibleHorizontalDivider
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet(
    modifier: Modifier = Modifier,
    title: String,
    onClickCloseButton: () -> Unit,
    onDismissRequest: () -> Unit,
    isVisibleHorizontalDivider: Boolean,
    content: @Composable ColumnScope.() -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = {
            onDismissRequest()
        },
        dragHandle = {
            BottomSheetDefaults.DragHandle()
        },
        containerColor = com.flexath.resources.java.theme.colorBackground,
        contentColor = com.flexath.resources.java.theme.textColorPrimary,
        modifier = modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LargePadding2)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = com.flexath.resources.java.theme.textColorPrimary,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_close_bottom_sheet),
                contentDescription = "Close Button",
                modifier = Modifier.clickable {
                    onClickCloseButton()
                }
            )
        }

        if(isVisibleHorizontalDivider) {
            Spacer(modifier = Modifier.height(MediumPadding5))

            HorizontalDivider(
                thickness = Dimens.SmallPadding0,
                color = com.flexath.resources.java.theme.dividerColor
            )
        }

        content()

        Spacer(modifier = Modifier.height(LargePadding2))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomBottomSheetPreview() {
    CustomBottomSheet(
        modifier = Modifier.fillMaxWidth(),
        title = "All Categories",
        onClickCloseButton = {

        },
        onDismissRequest = {

        },
        isVisibleHorizontalDivider = true,
        content = {

        }
    )
}