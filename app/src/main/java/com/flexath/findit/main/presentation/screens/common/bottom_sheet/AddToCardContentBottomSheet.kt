package com.flexath.findit.main.presentation.screens.common.bottom_sheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flexath.findit.R
import com.flexath.findit.theme.colorBackground
import com.flexath.findit.theme.colorPrimary
import com.flexath.findit.theme.dividerColor
import com.flexath.findit.theme.textColorPrimary
import com.flexath.findit.theme.textColorSecondary
import com.flexath.findit.theme.variantSelectedBackgroundColor
import com.flexath.findit.main.presentation.screens.common.BottomSheetUtil
import com.flexath.findit.main.presentation.screens.common.CustomFilledButton
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.core.utils.Dimens.SmallPadding0
import com.flexath.findit.core.utils.Dimens.SmallPadding5

@Composable
fun AddToCartContentBottomSheet(
    modifier: Modifier = Modifier,
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    onClickAddToCardButton: () -> Unit
) {
    var quantity by rememberSaveable {
        mutableIntStateOf(1)
    }

    if (quantity <= 1) {
        quantity = 1
    }

    BottomSheetUtil(
        modifier = modifier.defaultMinSize(minHeight = 500.dp),
        title = stringResource(R.string.lbl_add_to_cart),
        bottomSheetShow = bottomSheetShow,
        onSheetShowChange = {
            onSheetShowChange(it)
        },
        content = {
            Spacer(modifier = Modifier.height(MediumPadding5))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2)
            ) {
                Text(
                    text = stringResource(R.string.lbl_quantity),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = textColorPrimary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = SmallPadding5)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        quantity--
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = "Minus Button"
                        )
                    }

                    Text(
                        text = quantity.toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = textColorPrimary,
                        modifier = Modifier.padding(horizontal = SmallPadding5)
                    )

                    IconButton(onClick = {
                        quantity++
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "Minus Button"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(MediumPadding5))

            HorizontalDivider(
                thickness = SmallPadding0,
                color = dividerColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2)
            )

            Spacer(modifier = Modifier.height(MediumPadding5))

            Text(
                text = stringResource(R.string.lbl_variant),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = textColorPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2)
            )

            Spacer(modifier = Modifier.height(MediumPadding5))

            // Add Variant Button Codes
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = LargePadding2,end = SmallPadding5)
                    .horizontalScroll(rememberScrollState())
            ) {
                ProductVariantButtons()
            }

            Spacer(modifier = Modifier.height(MediumPadding5))

            HorizontalDivider(
                thickness = SmallPadding0,
                color = dividerColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2)
            )

            Spacer(modifier = Modifier.height(MediumPadding5))

            Text(
                text = stringResource(R.string.lbl_total_price),
                style = MaterialTheme.typography.bodySmall,
                color = textColorSecondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2)
            )

            Spacer(modifier = Modifier.height(SmallPadding5))

            Text(
                text = "Rp 1.500.000",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = textColorPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2)
            )

            Spacer(modifier = Modifier.height(LargePadding2))

            CustomFilledButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2),
                text = stringResource(id = R.string.lbl_add_to_cart),
                onClick = {
                    onClickAddToCardButton()
                }
            )
        }
    )
}

val productVariantList = listOf(
    "Black",
    "White",
    "Blue",
    "Red",
    "Yellow"
)

@Composable
fun ProductVariantButtons() {
    productVariantList.forEach { variant ->
        var isSelected by rememberSaveable {
            mutableStateOf(false)
        }
        Button(
            onClick = {
                isSelected = !isSelected
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSelected) {
                    variantSelectedBackgroundColor
                } else {
                    colorBackground
                },
                contentColor = if (isSelected) {
                    colorPrimary
                } else {
                    textColorPrimary
                }
            ),
            border = if (isSelected) {
                null
            } else {
                BorderStroke(width = SmallPadding0, color = dividerColor)
            },
            shape = RoundedCornerShape(SmallPadding5),
            modifier = Modifier.padding(end = SmallPadding5)
        ) {
            Text(
                text = variant,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}