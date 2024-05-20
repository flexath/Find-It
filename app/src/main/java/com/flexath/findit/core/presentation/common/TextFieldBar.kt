package com.flexath.findit.core.presentation.common

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens.SmallPadding5
import com.flexath.findit.theme.colorPrimary
import com.flexath.findit.theme.hintColor
import com.flexath.findit.theme.searchBarBackgroundColor
import com.flexath.findit.theme.textColorPrimary

@Composable
fun TextFieldBar(
    context: Context,
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    query: String,
    onQueryChange: (String) -> Unit,
    isClickable: Boolean,
    onClickSearchBar: () -> Unit,
    onSearch: (String) -> Unit,
    isTrailingIconVisible: Boolean = true,
    placeholder: String = stringResource(id = R.string.lbl_search_hint),
    queryColor: Color = textColorPrimary,
    isError: Boolean = false
) {
    TextField(
        value = query,
        onValueChange = {
            onQueryChange(it)
        },
        enabled = isEnabled,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
            }
        ),
        isError = isError,
        trailingIcon = {
            if (isTrailingIconVisible) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon"
                )
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = hintColor
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = searchBarBackgroundColor,
            unfocusedContainerColor = searchBarBackgroundColor,
            disabledContainerColor = searchBarBackgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTextColor = queryColor,
            unfocusedTextColor = queryColor,
            errorContainerColor = searchBarBackgroundColor,
            errorTextColor = queryColor,
            errorIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .clip(RoundedCornerShape(SmallPadding5))
            .background(color = searchBarBackgroundColor)
            .clickable {
                if (isClickable) {
                    onClickSearchBar()
                }
            }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchBarPreview() {
    TextFieldBar(
        LocalContext.current,
        modifier = Modifier.fillMaxWidth(),
        isEnabled = false,
        query = "aung thiha",
        onQueryChange = {

        },
        isClickable = false,
        onClickSearchBar = {

        },
        onSearch = {

        },
        queryColor = colorPrimary
    )
}