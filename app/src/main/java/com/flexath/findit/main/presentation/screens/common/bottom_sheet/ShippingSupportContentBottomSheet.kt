package com.flexath.findit.main.presentation.screens.common.bottom_sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.flexath.findit.R
import com.flexath.findit.theme.dividerColor
import com.flexath.findit.theme.textColorPrimary
import com.flexath.findit.theme.textColorSecondary
import com.flexath.findit.main.presentation.screens.common.BottomSheetUtil
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.core.utils.Dimens.SmallPadding0
import com.flexath.findit.core.utils.Dimens.SmallPadding5

@Composable
fun ShippingSupportContentBottomSheet(
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit
) {
    BottomSheetUtil(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        title = stringResource(R.string.lbl_shipping_support),
        bottomSheetShow = bottomSheetShow,
        onSheetShowChange = {
            onSheetShowChange(it)
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2)
            ) {
                items(count = 2) {index ->

                    Spacer(modifier = Modifier.height(MediumPadding5))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dummy_shipping_support),
                            contentDescription = "Shipping Support Logo",
                            modifier = Modifier.weight(0.3f)
                        )

                        Column(
                            modifier = Modifier.weight(0.7f).padding(SmallPadding5)
                        ) {
                            Text(
                                text = "J&T",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = textColorPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(SmallPadding5))

                            Text(
                                text = "Regular, Express",
                                style = MaterialTheme.typography.bodySmall,
                                color = textColorSecondary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(MediumPadding5))

                    if(index < 1) {
                        HorizontalDivider(
                            thickness = SmallPadding0,
                            color = dividerColor
                        )
                    }
                }
            }
        }
    )
}