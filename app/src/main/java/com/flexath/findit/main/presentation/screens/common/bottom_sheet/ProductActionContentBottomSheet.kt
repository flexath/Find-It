package com.flexath.findit.main.presentation.screens.common.bottom_sheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.flexath.findit.R
import com.flexath.findit.theme.dividerColor
import com.flexath.findit.theme.textColorPrimary
import com.flexath.findit.main.presentation.screens.common.BottomSheetUtil
import com.flexath.findit.main.presentation.screens.common.CustomFilledButton
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.MediumPadding5

@Composable
fun ProductContentBottomSheet(
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    onClickCloseButton: () -> Unit,
    onClickAddToCardButton: () -> Unit
) {
    BottomSheetUtil(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        title = stringResource(R.string.lbl_product_action),
        bottomSheetShow = bottomSheetShow,
        onSheetShowChange = {
            onSheetShowChange(it)
        },
        content = {
            Spacer(modifier = Modifier.height(MediumPadding5))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2)
            ) {
                Text(
                    text = stringResource(R.string.lbl_add_to_wishlist),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = textColorPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClickCloseButton()
                        }
                )

                Spacer(modifier = Modifier.height(MediumPadding5))

                HorizontalDivider(
                    thickness = Dimens.SmallPadding0,
                    color = dividerColor,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(MediumPadding5))

                Text(
                    text = stringResource(R.string.lbl_share_product),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = textColorPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClickCloseButton()
                        }
                )

                Spacer(modifier = Modifier.height(LargePadding2))

                CustomFilledButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.lbl_add_to_cart),
                    onClick = {
                        onClickAddToCardButton()
                    }
                )
            }
        }
    )
}