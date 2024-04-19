package com.flexath.findit.main.presentation.screens.common

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens.SmallPadding5
import com.flexath.findit.theme.hintColor
import com.flexath.findit.theme.searchBarBackgroundColor
import com.flexath.findit.theme.textColorPrimary

@Composable
fun SearchBar(
    context: Context,
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    query: String,
    onQueryChange: (String) -> Unit,
    isClickable: Boolean,
    onClickSearchBar: () -> Unit,
    onSearch: () -> Unit
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
                onSearch()
            }
        ),
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon"
            )
        },
        placeholder = {
            Text(
                text = context.getString(R.string.lbl_search_hint),
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
            errorIndicatorColor = Color.Transparent,
            focusedTextColor = textColorPrimary
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
    SearchBar(
        LocalContext.current,
        isEnabled = false,
        modifier = Modifier,
        query = "aung thiha",
        isClickable = false,
        onClickSearchBar = {

        },
        onQueryChange = {

        },
        onSearch = {

        }
    )
}