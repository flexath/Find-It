package com.flexath.findit.presentation.ui.main.common

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
import com.flexath.findit.R
import com.flexath.findit.presentation.theme.colorBackground
import com.flexath.findit.presentation.theme.dividerColor
import com.flexath.findit.presentation.theme.textColorPrimary
import com.flexath.findit.presentation.utils.Dimens
import com.flexath.findit.presentation.utils.Dimens.LargePadding2
import com.flexath.findit.presentation.utils.Dimens.MediumPadding5

@Composable
fun BottomSheetUtil(
    title: String,
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    if (bottomSheetShow) {
        CustomBottomSheet(
            title = title,
            onClickCloseButton = {
                onSheetShowChange(false)
            },
            onDismissRequest = {
                onSheetShowChange(false)
            }
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
        containerColor = colorBackground,
        contentColor = textColorPrimary,
        modifier = modifier.fillMaxWidth()
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
                color = textColorPrimary,
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

        Spacer(modifier = Modifier.height(MediumPadding5))

        HorizontalDivider(
            thickness = Dimens.SmallPadding0,
            color = dividerColor
        )

        Spacer(modifier = Modifier.height(MediumPadding5))

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
        content = {

        }
    )
}