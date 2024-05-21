package com.flexath.findit.auth.presentation.screens.register

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import com.flexath.findit.core.presentation.events.AppCoreEvent
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.LargePadding5
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.core.utils.Dimens.SmallPadding5
import com.flexath.findit.main.presentation.screens.common.CustomFilledButton
import com.flexath.findit.theme.alertColor
import com.flexath.findit.theme.colorPrimary
import com.flexath.findit.theme.hintColor

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    context: Context,
    modifier: Modifier = Modifier,
    onClickRegisterButton: (AppCoreEvent) -> Unit
) {
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

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        ConstraintLayout {
            val (headerSectionRef,
                usernameRef,
                usernameErrorRef,
                emailRef,
                emailErrorRef,
                passwordRef,
                passwordErrorRef,
                repeatedPasswordRef,
                repeatedPasswordErrorRef
            ) = createRefs()

            val verticalGuide80 = createGuidelineFromEnd(0.8f)

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
                query = authState.userName,
                onQueryChange = {
                    viewModel.onRegistrationEvent(RegistrationFormEvent.UserNameChanged(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(usernameRef) {
                        top.linkTo(headerSectionRef.bottom, margin = LargePadding5)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            )

            if (authState.userNameError != null) {
                Text(
                    text = authState.userNameError,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.End,
                    color = alertColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .constrainAs(usernameErrorRef) {
                            top.linkTo(usernameRef.bottom, margin = SmallPadding5)
                            end.linkTo(parent.end, margin = LargePadding5)
                            start.linkTo(verticalGuide80)
                            width = Dimension.fillToConstraints
                        }
                )
            }

            TextFieldWithTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(emailRef) {
                        top.linkTo(
                            usernameRef.bottom, margin = if (authState.userNameError != null) {
                                LargePadding5
                            } else {
                                MediumPadding5
                            }
                        )
                        width = Dimension.matchParent
                        height = Dimension.wrapContent
                    },
                context = context,
                title = stringResource(R.string.lbl_email_phone),
                placeholder = stringResource(R.string.lbl_enter_email_phone),
                query = authState.email,
                onQueryChange = {
                    viewModel.onRegistrationEvent(RegistrationFormEvent.EmailChanged(it))
                }
            )

            if (authState.emailError != null) {
                Text(
                    text = authState.emailError,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.End,
                    color = alertColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .constrainAs(emailErrorRef) {
                            top.linkTo(emailRef.bottom, margin = SmallPadding5)
                            end.linkTo(parent.end, margin = LargePadding5)
                            start.linkTo(verticalGuide80)
                            width = Dimension.fillToConstraints
                        }
                )
            }

            PasswordTextFieldWithTitle(
                title = stringResource(R.string.lbl_password),
                query = authState.password,
                onQueryChange = {
                    viewModel.onRegistrationEvent(RegistrationFormEvent.PasswordChanged(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(passwordRef) {
                        top.linkTo(
                            emailRef.bottom, margin = if (authState.emailError != null) {
                                LargePadding5
                            } else {
                                MediumPadding5
                            }
                        )
                        width = Dimension.matchParent
                        height = Dimension.wrapContent
                    }
            )

            if (authState.passwordError != null) {
                Text(
                    text = authState.passwordError,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.End,
                    color = alertColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .constrainAs(passwordErrorRef) {
                            top.linkTo(passwordRef.bottom, margin = SmallPadding5)
                            end.linkTo(parent.end, margin = LargePadding5)
                            start.linkTo(verticalGuide80)
                            width = Dimension.fillToConstraints
                        }
                )
            }

            PasswordTextFieldWithTitle(
                title = stringResource(R.string.lbl_password_confirmation),
                query = authState.repeatedPassword,
                onQueryChange = {
                    viewModel.onRegistrationEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(repeatedPasswordRef) {
                        top.linkTo(
                            passwordRef.bottom, margin = if (authState.passwordError != null) {
                                LargePadding5
                            } else {
                                MediumPadding5
                            }
                        )
                        width = Dimension.matchParent
                        height = Dimension.wrapContent
                    }
            )

            if (authState.repeatedPasswordError != null) {
                Text(
                    text = authState.repeatedPasswordError,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.End,
                    color = alertColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .constrainAs(repeatedPasswordErrorRef) {
                            top.linkTo(repeatedPasswordRef.bottom, margin = SmallPadding5)
                            end.linkTo(parent.end, margin = LargePadding5)
                            start.linkTo(verticalGuide80)
                            width = Dimension.fillToConstraints
                        }
                )
            }
        }

        CustomFilledButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LargePadding2),
            text = stringResource(R.string.lbl_register),
            onClick = {
                viewModel.onRegistrationEvent(RegistrationFormEvent.Submit)
            }
        )

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
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
        onClickRegisterButton = {

        },
        viewModel = hiltViewModel()
    )
}