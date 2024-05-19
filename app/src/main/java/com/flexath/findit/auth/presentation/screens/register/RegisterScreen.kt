package com.flexath.findit.auth.presentation.screens.register

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.flexath.findit.R
import com.flexath.findit.auth.presentation.screens.common.HeaderSection
import com.flexath.findit.auth.presentation.screens.common.TextFieldWithTitle
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.LargePadding5
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.main.presentation.screens.common.DetailTopAppBar
import com.flexath.findit.theme.colorPrimary
import com.flexath.findit.theme.hintColor

@Composable
fun RegisterScreen(
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit
) {
    var userNameQuery by remember {
        mutableStateOf("")
    }

    var emailOrPhoneQuery by remember {
        mutableStateOf("")
    }

    var passwordQuery by remember {
        mutableStateOf("")
    }

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (topAppBarRef,
            headerSectionRef,
            usernameRef,
            emailRef,
            passwordRef,
            haveAnAccountRef) = createRefs()

        DetailTopAppBar(
            title = "",
            onClickBackButton = {
                onClickBackButton()
            },
            modifier = Modifier
                .constrainAs(topAppBarRef) {
                top.linkTo(parent.top)
                width = Dimension.matchParent
                height = Dimension.wrapContent
            }
        )

        HeaderSection(
            title = stringResource(R.string.lbl_register_account),
            description = stringResource(R.string.lbl_create_your_own_account),
            modifier = Modifier
                .constrainAs(headerSectionRef) {
                    top.linkTo(topAppBarRef.bottom, margin = LargePadding5)
                    start.linkTo(parent.start, margin = LargePadding2)
                    end.linkTo(parent.end, margin = LargePadding2)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                }
                .fillMaxWidth()
                .padding(top = LargePadding5)
        )

        TextFieldWithTitle(
            context = context,
            title = stringResource(R.string.lbl_username),
            placeholder = stringResource(R.string.lbl_enter_your_username),
            text = userNameQuery,
            onQueryChange = {
                userNameQuery = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(usernameRef) {
                    top.linkTo(headerSectionRef.bottom, margin = LargePadding5)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        )

        TextFieldWithTitle(
            context = context,
            title = stringResource(R.string.lbl_email_phone),
            placeholder = stringResource(R.string.lbl_enter_email_phone),
            text = emailOrPhoneQuery,
            onQueryChange = {
                emailOrPhoneQuery = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(emailRef) {
                    top.linkTo(usernameRef.bottom, margin = MediumPadding5)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                }
        )

        TextFieldWithTitle(
            context = context,
            title = "Password",
            placeholder = stringResource(R.string.lbl_enter_your_password),
            text = passwordQuery,
            onQueryChange = {
                passwordQuery = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(passwordRef) {
                    top.linkTo(emailRef.bottom, margin = MediumPadding5)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                }
        )

        Row(
            modifier = Modifier.constrainAs(haveAnAccountRef) {
                bottom.linkTo(parent.bottom, LargePadding5)
                centerHorizontallyTo(parent)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        ) {
            Text(
                text = stringResource(R.string.lbl_have_an_account),
                color = hintColor,
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = stringResource(R.string.lbl_sign_in),
                color = colorPrimary,
                style = MaterialTheme.typography.titleSmall
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(
        context = LocalContext.current,
        modifier = Modifier.fillMaxSize(),
        onClickBackButton = {

        }
    )
}