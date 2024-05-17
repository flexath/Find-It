package com.flexath.findit.auth.presentation.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.theme.textColorPrimary

@Composable
fun HeaderSection(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = textColorPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(MediumPadding5))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = textColorPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HeaderSectionPreview() {
    HeaderSection(
        title = "Register",
        description = "Register Email/Mobile Number",
        modifier = Modifier.fillMaxWidth()
    )
}