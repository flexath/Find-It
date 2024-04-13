package com.flexath.findit.presentation.ui.main

import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.findit.R
import com.flexath.findit.presentation.theme.colorPrimary
import com.flexath.findit.presentation.theme.shadowColor
import com.flexath.findit.presentation.utils.Dimens.SmallPadding2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    onClickBellIcon: () -> Unit,
    onClickCartIcon: () -> Unit
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                color = colorPrimary
            )
        },
        actions = {
            Row {
                IconButton(
                    onClick = {
                        onClickBellIcon()
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bell),
                        contentDescription = "bell icon"
                    )
                }

                IconButton(
                    onClick = {
                        onClickCartIcon()
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = "cart icon"
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = SmallPadding2,
                spotColor = shadowColor
            )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainTopBarPreview() {
    MainTopBar(
        onClickBellIcon = {

        },
        onClickCartIcon = {

        }
    )
}