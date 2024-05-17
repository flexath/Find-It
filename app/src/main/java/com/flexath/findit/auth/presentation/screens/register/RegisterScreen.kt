package com.flexath.findit.auth.presentation.screens.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.flexath.findit.R
import com.flexath.findit.auth.presentation.screens.common.HeaderSection
import com.flexath.findit.core.utils.Dimens.LargePadding5
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.main.presentation.screens.common.DetailTopAppBar
import com.flexath.findit.theme.textColorPrimary

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit
) {
        Scaffold(
            topBar = {

            },
            modifier = modifier
        ) {
            val topPadding = it.calculateTopPadding()

            ConstraintLayout(
                modifier = modifier
            ) {
                val (topAppBarRef,headerSectionRef,emailOrPhoneTitleRef) = createRefs()

                DetailTopAppBar(
                    title = "",
                    onClickBackButton = {
                        onClickBackButton()
                    },
                    modifier = Modifier.constrainAs(topAppBarRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                )

                HeaderSection(
                    title = stringResource(R.string.lbl_register_account), description = stringResource(
                        R.string.lbl_enter_email_phone_number_to_register
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .constrainAs(headerSectionRef) {
                            start.linkTo(parent.start, margin = MediumPadding5)
                            end.linkTo(parent.end, margin = MediumPadding5)
                        }
                        .padding(top = topPadding, start = MediumPadding5, end = MediumPadding5)
                        .padding(top = LargePadding5)
                )

                Text(
                    text = "Email/Phone",
                    modifier = Modifier.constrainAs(emailOrPhoneTitleRef) {
                        start.linkTo(parent.start, margin = MediumPadding5)
                        top.linkTo(headerSectionRef.bottom, margin = LargePadding5)
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColorPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(
        modifier = Modifier.fillMaxSize(),
        onClickBackButton = {

        }
    )
}