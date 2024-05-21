package com.flexath.findit.auth.presentation.screens

import android.content.Context
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.flexath.findit.R
import com.flexath.findit.auth.presentation.screens.common.OtpTextField
import com.flexath.findit.auth.presentation.view_models.AuthViewModel
import com.flexath.findit.core.presentation.events.AppCoreEvent
import com.flexath.findit.core.utils.Dimens.ExtraLargePadding5
import com.flexath.findit.core.utils.Dimens.ExtraLargePadding5_2x
import com.flexath.findit.core.utils.Dimens.LargePadding2
import com.flexath.findit.core.utils.Dimens.LargePadding5
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.core.utils.Dimens.SmallPadding5
import com.flexath.findit.main.presentation.screens.common.CustomFilledButton
import com.flexath.findit.theme.textColorPrimary

@Composable
fun VerificationScreen(
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    context: Context,
    emailOrPhone: String,
    onClickConfirmButton: (AppCoreEvent) -> Unit
) {
    var otp by remember {
        mutableStateOf("")
    }

    var isOtpFull by remember {
        mutableStateOf(false)
    }

    ConstraintLayout(
        modifier = modifier
    ) {
        val (
            headerSectionTitleRef,
            headerSectionTitleDesc,
            verificationCodeRef,
            resendCodeRef,
            otpRef,
            continueButtonRef,
        ) = createRefs()

        Text(
            text = stringResource(R.string.lbl_verification),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Start,
            color = textColorPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(headerSectionTitleRef) {
                top.linkTo(parent.top, margin = ExtraLargePadding5_2x)
                start.linkTo(parent.start, margin = LargePadding2)
            }
        )

        Text(
            text = "We have sent a verification code to $emailOrPhone",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            textAlign = TextAlign.Start,
            color = textColorPrimary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = LargePadding2)
                .fillMaxWidth()
                .constrainAs(headerSectionTitleDesc) {
                    top.linkTo(headerSectionTitleRef.bottom, margin = SmallPadding5)
                }
        )

        Text(
            text = "Verification Code",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            textAlign = TextAlign.Start,
            color = textColorPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(verificationCodeRef) {
                start.linkTo(parent.start, margin = LargePadding2)
                top.linkTo(headerSectionTitleDesc.bottom, margin = ExtraLargePadding5)
            }
        )

        Text(
            text = "Resend Code",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            textAlign = TextAlign.End,
            color = textColorPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(resendCodeRef) {
                    end.linkTo(parent.end, margin = LargePadding2)
                    top.linkTo(headerSectionTitleDesc.bottom, margin = ExtraLargePadding5)
                }
                .clickable {

                }
        )

        OtpTextField(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(otpRef) {
                    start.linkTo(verificationCodeRef.start, margin = LargePadding2)
                    end.linkTo(resendCodeRef.end, margin = LargePadding2)
                    top.linkTo(verificationCodeRef.bottom, margin = MediumPadding5)
                },
            otpText = otp,
            otpCount = 4,
            onOtpTextChange = { otpCode, isFull ->
                otp = otpCode
                isOtpFull = isFull
            }
        )

        CustomFilledButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LargePadding2)
                .constrainAs(continueButtonRef) {
                    bottom.linkTo(parent.bottom, LargePadding5)
                },
            text = stringResource(R.string.lbl_confirm),
            isEnabled = isOtpFull,
            onClick = {
                onClickConfirmButton(AppCoreEvent.AuthEvent)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun VerificationScreenPreview() {
    VerificationScreen(
        viewModel = hiltViewModel(),
        context = LocalContext.current,
        emailOrPhone = "09795558753",
        onClickConfirmButton = {

        }
    )
}