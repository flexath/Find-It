package com.flexath.findit.presentation.ui.main.category

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.presentation.ui.main.common.CustomOutlinedButton
import com.flexath.findit.presentation.ui.main.common.DetailTopAppBarWithOneAction
import com.flexath.findit.presentation.ui.main.common.ProductCardGrid
import com.flexath.findit.presentation.ui.main.common.SearchBar
import com.flexath.findit.presentation.utils.Dimens.LargePadding2

@Composable
fun CategoryScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
) {
    var query by remember {
        mutableStateOf("")
    }

    Box(
        modifier = modifier
    ) {
        Column{
            DetailTopAppBarWithOneAction(
                title = stringResource(R.string.lbl_category),
                actionIcon = R.drawable.ic_cart,
                onClickBackButton = {
                    onClickBackButton()
                },
                onClickActionButton = {
                    // Share news article code
                }
            )

            Spacer(modifier = Modifier.height(LargePadding2))

            Text(
                text = "Gadget",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(horizontal = LargePadding2)
            )

            Spacer(modifier = Modifier.height(LargePadding2))

            SearchBar(
                context = context,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LargePadding2),
                query = query,
                isEnabled = true,
                isClickable = false,
                onClickSearchBar = {

                },
                onQueryChange = {
                    query = it
                },
                onSearch = {

                }
            )

            Spacer(modifier = Modifier.height(LargePadding2))

            ProductCardGrid(
                modifier = Modifier.fillMaxWidth(),
                onClickVerticalDots = {

                },
                onClickProductCard = {

                }
            )
        }

        CustomOutlinedButton(
            text = stringResource(R.string.lbl_filter_sorting),
            modifier = Modifier.fillMaxWidth().padding(LargePadding2).align(Alignment.BottomCenter)
        ) {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoryScreenPreview() {
    CategoryScreen(
        context = LocalContext.current,
    ) {}

}