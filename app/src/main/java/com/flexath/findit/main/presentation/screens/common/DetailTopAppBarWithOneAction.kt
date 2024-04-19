package com.flexath.findit.main.presentation.screens.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens
import com.flexath.findit.theme.shadowColor
import com.flexath.findit.theme.textColorPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBarWithOneAction(
    title: String,
    actionIcon: Int,
    onClickBackButton: () -> Unit,
    onClickActionButton: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Medium,
                ),
                color = textColorPrimary
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onClickBackButton()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back Button"
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onClickActionButton()
                }
            ) {
                Icon(
                    painter = painterResource(id = actionIcon),
                    contentDescription = "Action Button"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = Dimens.SmallPadding2,
                spotColor = shadowColor
            )
    )
}

@Preview
@Composable
private fun DetailTopAppBarWithOneActionPreview() {
    DetailTopAppBarWithOneAction(
        title = "News",
        actionIcon = R.drawable.ic_share,
        onClickBackButton = {

        },
        onClickActionButton = {

        }
    )

}