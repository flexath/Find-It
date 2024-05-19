package com.flexath.findit.auth.presentation.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.theme.textColorPrimary

@Composable
fun PasswordTextFieldWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    query: String,
    onQueryChange: (String) -> Unit
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

        PasswordTextField(
            query = query,
            onQueryChange = onQueryChange,
            modifier = modifier.padding(horizontal = Dimens.LargePadding2),
        )
    }
}