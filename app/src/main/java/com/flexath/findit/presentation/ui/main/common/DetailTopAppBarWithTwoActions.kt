package com.flexath.findit.presentation.ui.main.common

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
import com.flexath.findit.presentation.theme.shadowColor
import com.flexath.findit.presentation.theme.textColorPrimary
import com.flexath.findit.presentation.utils.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBarWithTwoActions(
    title: String,
    firstActionIcon: Int,
    secondActionIcon: Int,
    onClickBackButton: () -> Unit,
    onClickFirstActionButton: () -> Unit,
    onClickSecondActionButton: () -> Unit,
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
                    onClickFirstActionButton()
                }
            ) {
                Icon(
                    painter = painterResource(id = firstActionIcon),
                    contentDescription = "Action Button One"
                )
            }

            IconButton(
                onClick = {
                    onClickSecondActionButton()
                }
            ) {
                Icon(
                    painter = painterResource(id = secondActionIcon),
                    contentDescription = "Action Button Two"
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
private fun DetailTopAppBarWithTwoActionsPreview() {
    DetailTopAppBarWithTwoActions(
        title = "News",
        firstActionIcon = R.drawable.ic_share,
        secondActionIcon = R.drawable.ic_cart,
        onClickBackButton = {

        },
        onClickFirstActionButton = {

        },
        onClickSecondActionButton = {

        }
    )

}