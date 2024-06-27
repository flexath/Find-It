package com.flexath.findit.major.presentation.screens.common.bottom_sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.flexath.resources.R
import com.flexath.core.presentation.common.CustomFilledButton
import com.flexath.core.presentation.screens.components.CustomOutlinedButton
import com.flexath.core.utils.Dimens.LargePadding2
import com.flexath.core.utils.Dimens.MediumPadding5
import com.flexath.core.utils.Dimens.SmallPadding0
import com.flexath.core.utils.Dimens.SmallPadding5
import com.flexath.findit.major.presentation.screens.common.BottomSheetUtil

@Composable
fun SortingContentBottomSheet(
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    onClickApplyButton: () -> Unit
) {
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    BottomSheetUtil(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        title = stringResource(R.string.lbl_sorting),
        bottomSheetShow = bottomSheetShow,
        onSheetShowChange = {
            onSheetShowChange(it)
        },
        content = {

            CustomRadioGroup(
                selectedIndex = selectedIndex,
                onSelectChange = {
                    selectedIndex = it
                }
            )

            Spacer(modifier = Modifier.height(SmallPadding5))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2)
            ) {
                CustomOutlinedButton(
                    text = stringResource(R.string.lbl_reset),
                    modifier = Modifier.weight(1f)
                ) {
                    selectedIndex = 0
                }

                Spacer(modifier = Modifier.width(MediumPadding5))

                CustomFilledButton(
                    text = stringResource(R.string.lbl_apply),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onClickApplyButton()
                    }
                )
            }

        }
    )
}

data class RadioButtonContent(
    val title: String,
    val isSelected: Boolean
)

val radioButtonList = listOf(
    RadioButtonContent(
        title = "Name (A-Z)",
        isSelected = true
    ),
    RadioButtonContent(
        title = "Name (Z-A)",
        isSelected = false
    ),
    RadioButtonContent(
        title = "Price (High-Low)",
        isSelected = false
    ),
    RadioButtonContent(
        title = "Price (Low-High)",
        isSelected = false
    )
)

@Composable
fun CustomRadioGroup(
    selectedIndex: Int,
    onSelectChange: (Int) -> Unit
) {
    radioButtonList.forEachIndexed { index, radioButton ->
        CustomRadioButton(
            title = radioButton.title,
            selectedIndex = index,
            isSelected = selectedIndex == index,
            onSelectChange = {
                onSelectChange(it)
            }
        )
    }
}

@Composable
fun CustomRadioButton(
    title: String,
    selectedIndex: Int,
    isSelected: Boolean,
    onSelectChange: (Int) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onSelectChange(selectedIndex)
                }
                .padding(LargePadding2)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = com.flexath.resources.java.theme.textColorPrimary
            )

            Spacer(modifier = Modifier.weight(1f))

            if (isSelected) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tick),
                    contentDescription = "Radio Tick"
                )
            }
        }

        if (selectedIndex != radioButtonList.lastIndex) {
            HorizontalDivider(
                thickness = SmallPadding0,
                color = com.flexath.resources.java.theme.dividerColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2)
            )
        }
    }
}