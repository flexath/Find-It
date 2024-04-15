package com.flexath.findit.presentation.ui.main.common

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flexath.findit.presentation.ui.main.home.components.ProductCardList
import com.flexath.findit.presentation.ui.main.home.components.TitleSection
import com.flexath.findit.presentation.utils.Dimens

@Composable
fun ProductItemSection(
    context: Context,
    title: String
) {
    Column {
        TitleSection(title = title) {
            // add SeeAll text button clicking codes
            Toast.makeText(context, "SeeAll is clicked", Toast.LENGTH_SHORT).show()
        }

        Spacer(modifier = Modifier.height(Dimens.SmallPadding4))

        ProductCardList(
            modifier = Modifier
                .fillMaxWidth(),
            onClickProductCard = {

            },
            onClickVerticalDots = {

            }
        )
    }
}