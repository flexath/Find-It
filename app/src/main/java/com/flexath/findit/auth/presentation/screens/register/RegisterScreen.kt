package com.flexath.findit.auth.presentation.screens.register

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.flexath.findit.R
import com.flexath.findit.auth.presentation.events.RegistrationFormEvent
import com.flexath.findit.auth.presentation.events.ValidationEvent
import com.flexath.findit.auth.presentation.screens.common.HeaderSection
import com.flexath.findit.auth.presentation.screens.common.PasswordTextFieldWithTitle
import com.flexath.findit.auth.presentation.screens.common.TextFieldWithTitle
import com.flexath.findit.auth.presentation.view_models.AuthViewModel
import com.flexath.findit.core.presentation.common.TextFieldBar
import com.flexath.findit.core.presentation.events.AppCoreEvent
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.LargePadding5
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.main.presentation.screens.common.CustomFilledButton
import com.flexath.findit.theme.alertColor
import com.flexath.findit.theme.colorPrimary
import com.flexath.findit.theme.hintColor
import com.flexath.findit.theme.textColorPrimary

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    context: Context,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickRegisterButton: (AppCoreEvent) -> Unit
) {
    var userNameQuery by remember {
        mutableStateOf("")
    }

    var userNameQueryColor by remember {
        mutableStateOf(textColorPrimary)
    }

    var emailOrPhoneQuery by remember {
        mutableStateOf("")
    }

    var emailOrPhoneQueryColor by remember {
        mutableStateOf(textColorPrimary)
    }

    var passwordQuery by remember {
        mutableStateOf("")
    }

    var passwordQueryColor by remember {
        mutableStateOf(textColorPrimary)
    }

    var repeatedPasswordQuery by remember {
        mutableStateOf("")
    }

    var repeatedPasswordColor by remember {
        mutableStateOf(textColorPrimary)
    }

    val authState = viewModel.authState.value

    LaunchedEffect(key1 = context) {
        viewModel.validationEvent.collect {
            when (it) {
                ValidationEvent.Success -> {
                    onClickRegisterButton(AppCoreEvent.AuthEvent)
                }
            }
        }
    }

    ConstraintLayout(
        modifier = modifier
    ) {
        val (headerSectionRef,
            usernameRef,
            emailRef,
            passwordRef,
            repeatedPasswordRef,
            haveAnAccountRef,
            confirmButtonRef
        ) = createRefs()

        HeaderSection(
            title = stringResource(R.string.lbl_register_account),
            description = stringResource(R.string.lbl_create_your_own_account),
            modifier = Modifier
                .constrainAs(headerSectionRef) {
                    top.linkTo(parent.top, margin = LargePadding5)
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
            query = if (authState.userNameError != null) {
                userNameQueryColor = alertColor
                authState.userNameError
            } else {
                userNameQueryColor = textColorPrimary
                authState.userName
            },
            onQueryChange = {
                viewModel.onRegistrationEvent(RegistrationFormEvent.UserNameChanged(it))
            },
            queryColor = userNameQueryColor,
            isError = authState.userNameError != null,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(usernameRef) {
                    top.linkTo(headerSectionRef.bottom, margin = LargePadding5)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        )

//        TextFieldWithTitle(
//            modifier = Modifier
//                .fillMaxWidth()
//                .constrainAs(emailRef) {
//                    top.linkTo(usernameRef.bottom, margin = MediumPadding5)
//                    width = Dimension.matchParent
//                    height = Dimension.wrapContent
//                },
//            context = context,
//            title = stringResource(R.string.lbl_email_phone),
//            placeholder = stringResource(R.string.lbl_enter_email_phone),
//            query = if (authState.emailError != null) {
//                emailOrPhoneQueryColor = alertColor
//                authState.emailError
//            } else {
//                emailOrPhoneQueryColor = textColorPrimary
//                authState.email
//            },
//            onQueryChange = {
//                viewModel.onRegistrationEvent(RegistrationFormEvent.EmailChanged(it))
//            },
//            queryColor = emailOrPhoneQueryColor,
//            isError = authState.emailError != null
//        )

        TextFieldBar(
            context = context,
            isEnabled = true,
            query = if (authState.emailError != null) {
                emailOrPhoneQueryColor = alertColor
                authState.emailError
            } else {
                emailOrPhoneQueryColor = textColorPrimary
                authState.email
            },
            queryColor = emailOrPhoneQueryColor,
            isError = authState.emailError != null,
            isClickable = true,
            onClickSearchBar = {

            },
            onQueryChange = {
                viewModel.onRegistrationEvent(RegistrationFormEvent.EmailChanged(it))
            },
            onSearch = {

            },
            isTrailingIconVisible = false,
            placeholder = stringResource(R.string.lbl_enter_email_phone),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LargePadding2)
                .constrainAs(emailRef) {
                    top.linkTo(usernameRef.bottom, margin = MediumPadding5)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                },
        )

        PasswordTextFieldWithTitle(
            title = stringResource(R.string.lbl_password),
            query = authState.password,
            onQueryChange = {
                viewModel.onRegistrationEvent(RegistrationFormEvent.PasswordChanged(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(passwordRef) {
                    top.linkTo(emailRef.bottom, margin = MediumPadding5)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                }
        )

        PasswordTextFieldWithTitle(
            title = stringResource(R.string.lbl_password_confirmation),
            query = authState.repeatedPassword,
            onQueryChange = {
                viewModel.onRegistrationEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(repeatedPasswordRef) {
                    top.linkTo(passwordRef.bottom, margin = MediumPadding5)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                }
        )

        CustomFilledButton(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(confirmButtonRef) {
                    start.linkTo(parent.start, margin = LargePadding2)
                    end.linkTo(parent.end, margin = LargePadding2)
                    top.linkTo(repeatedPasswordRef.bottom, margin = LargePadding5)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                },
            text = stringResource(R.string.lbl_register),
            onClick = {
                viewModel.onRegistrationEvent(RegistrationFormEvent.Submit)
            }
        )

        Row(
            modifier = Modifier.constrainAs(haveAnAccountRef) {
                top.linkTo(confirmButtonRef.bottom)
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

        },
        onClickRegisterButton = {

        },
        viewModel = hiltViewModel()
    )
}