package com.flexath.findit.auth.presentation.screens.common

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.flexath.findit.core.presentation.common.TextFieldBar
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.theme.textColorPrimary

@Composable
fun TextFieldWithTitle(
    modifier: Modifier = Modifier,
    context: Context,
    title: String,
    placeholder: String,
    query: String,
    onQueryChange: (String) -> Unit,
    queryColor: Color,
    isError: Boolean
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = textColorPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(horizontal = Dimens.LargePadding2)
        )

        Spacer(modifier = modifier.height(Dimens.SmallPadding5))

        TextFieldBar(
            context = context,
            modifier = modifier
                .padding(horizontal = Dimens.LargePadding2),
            isEnabled = true,
            query = query,
            queryColor = queryColor,
            isError = isError,
            isClickable = true,
            onClickSearchBar = {

            },
            onQueryChange = {
                onQueryChange(it)
            },
            onSearch = {

            },
            isTrailingIconVisible = false,
            placeholder = placeholder
        )
    }
}