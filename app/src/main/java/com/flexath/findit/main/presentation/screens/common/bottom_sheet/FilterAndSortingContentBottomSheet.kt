package com.flexath.findit.main.presentation.screens.common.bottom_sheet

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.flexath.findit.R
import com.flexath.findit.theme.colorBackground
import com.flexath.findit.theme.colorPrimary
import com.flexath.findit.theme.dividerColor
import com.flexath.findit.theme.textColorPrimary
import com.flexath.findit.theme.variantSelectedBackgroundColor
import com.flexath.findit.main.presentation.screens.common.BottomSheetUtil
import com.flexath.findit.main.presentation.screens.common.CustomFilledButton
import com.flexath.findit.main.presentation.screens.common.CustomOutlinedButton
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.MediumPadding3
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.core.utils.Dimens.SmallPadding0
import com.flexath.findit.core.utils.Dimens.SmallPadding5
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilterAndSortingContentBottomSheet(
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    onClickApplyButton: () -> Unit,
) {
    BottomSheetUtil(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        title = stringResource(R.string.lbl_filter_sorting),
        bottomSheetShow = bottomSheetShow,
        onSheetShowChange = {
            onSheetShowChange(it)
        },
        isVisibleHorizontalDivider = false,
        content = {
            var selectedTabIndex by rememberSaveable {
                mutableIntStateOf(0)
            }

            var selectedSortingIndex by rememberSaveable {
                mutableIntStateOf(0)
            }

            val pagerState = rememberPagerState {
                2
            }

            LaunchedEffect(key1 = selectedTabIndex) {
                pagerState.animateScrollToPage(selectedTabIndex)
            }

            LaunchedEffect(key1 = pagerState.currentPage, key2 = pagerState.isScrollInProgress) {
                if (!pagerState.isScrollInProgress) {
                    selectedTabIndex = pagerState.currentPage
                }
            }

            val tabItemList = listOf("Filter", "Sorting")

            Spacer(modifier = Modifier.height(LargePadding2))

            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = colorBackground,
                contentColor = textColorPrimary
            ) {
                tabItemList.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(
                                text = tabItem,
                                style = MaterialTheme.typography.bodyMedium,
                                color = textColorPrimary
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
            ) { index ->
                Column {
                    if (index == 0) {
                        val priceRange by remember {
                            mutableStateOf((5000f..1000000f))
                        }

                        var sliderPosition by remember {
                            mutableStateOf((50000f..500000f))
                        }

                        FilterPage(
                            priceRange = priceRange,
                            sliderPosition = sliderPosition,
                            onSliderPositionChange = {
                                sliderPosition = it
                            }
                        )
                    } else {
                        SortingPage(
                            selectedIndex = selectedSortingIndex,
                            onSelectChange = {
                                selectedSortingIndex = it
                            }
                        )
                    }

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
                            selectedSortingIndex = 0
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
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterPage(
    priceRange: ClosedFloatingPointRange<Float>,
    sliderPosition: ClosedFloatingPointRange<Float>,
    onSliderPositionChange: (ClosedFloatingPointRange<Float>) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LargePadding2)
    ) {
        item {
            Spacer(modifier = Modifier.height(MediumPadding5))

            Text(
                text = stringResource(R.string.lbl_price_range),
                style = MaterialTheme.typography.bodyMedium,
                color = textColorPrimary,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(MediumPadding3))

            RangeSlider(
                value = sliderPosition,
                onValueChange = {
                    onSliderPositionChange(it)
                },
                valueRange = priceRange,
                steps = 5000,
                colors = SliderDefaults.colors(
                    activeTickColor = Color.Transparent,
                    activeTrackColor = colorPrimary,
                    thumbColor = colorPrimary,
                    inactiveTrackColor = variantSelectedBackgroundColor
                ),
                startThumb = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_slider_thumb),
                        contentDescription = "Start Thumb"
                    )
                },
                endThumb = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_slider_thumb),
                        contentDescription = "End Thumb"
                    )
                }
            )

            Spacer(modifier = Modifier.height(SmallPadding5))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${sliderPosition.start.roundToInt()} MMK",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = textColorPrimary
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "${sliderPosition.endInclusive.roundToInt()} MMK",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = textColorPrimary
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(MediumPadding5))
        }

        filterCategories()
    }
}

@Composable
fun SortingPage(
    selectedIndex: Int,
    onSelectChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomRadioGroup(
            selectedIndex = selectedIndex,
            onSelectChange = {
                onSelectChange(it)
            }
        )
    }
}

fun LazyListScope.filterCategories() {
    items(count = 4) { _ ->
        var isSelected by rememberSaveable {
            mutableStateOf(false)
        }

        HorizontalDivider(
            thickness = SmallPadding0,
            color = dividerColor
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isSelected = !isSelected
                }
                .padding(LargePadding2)
        ) {
            Text(
                text = "Headphone",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = textColorPrimary
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = if (isSelected) {
                    painterResource(id = R.drawable.ic_check_active)
                } else {
                    painterResource(id = R.drawable.ic_check_inactive)
                },
                contentDescription = "CheckBox Button"
            )
        }
    }
}