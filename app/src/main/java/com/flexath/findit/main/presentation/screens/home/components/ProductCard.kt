package com.flexath.findit.main.presentation.screens.home.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.flexath.findit.R
import com.flexath.findit.core.utils.Dimens.MediumPadding3
import com.flexath.findit.core.utils.Dimens.MediumPadding5
import com.flexath.findit.core.utils.Dimens.ProductCardWidth
import com.flexath.findit.core.utils.Dimens.SmallPadding0
import com.flexath.findit.core.utils.Dimens.SmallPadding1
import com.flexath.findit.core.utils.Dimens.SmallPadding2
import com.flexath.findit.core.utils.Dimens.SmallPadding4
import com.flexath.findit.core.utils.Dimens.SmallPadding5
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.theme.alertColor
import com.flexath.findit.theme.colorBackground
import com.flexath.findit.theme.textColorPrimary

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    onClickProductCard: (id: Int) -> Unit,
    onClickVerticalDots: () -> Unit,
    product: ProductVO?
) {
    Surface(
        color = colorBackground,
        shape = RoundedCornerShape(MediumPadding3),
        tonalElevation = SmallPadding0,
        shadowElevation = SmallPadding0,
        modifier = modifier
            .width(width = ProductCardWidth)
            .padding(all = SmallPadding4)
            .clickable {
                onClickProductCard(1)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SmallPadding5),
        ) {
            Spacer(modifier = Modifier.height(MediumPadding3))

            AsyncImage(
                model = ImageRequest.Builder(context).data(product?.thumbnail.orEmpty()).build(),
                contentDescription = product?.title.orEmpty(),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(125.dp)
                    .clip(RoundedCornerShape(MediumPadding3))
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(MediumPadding5))

            Text(
                text = product?.title ?: "Item Title",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = textColorPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(SmallPadding2))

            Text(
                text = "${product?.price}$",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = alertColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(SmallPadding5))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                        contentDescription = "Rating Star",
                        tint = Color(0xFFFFC120)
                    )

                    Text(
                        text = product?.rating.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = textColorPrimary,
                        maxLines = 1,
                        modifier = Modifier.padding(start = SmallPadding1)
                    )

                    Text(
                        text = if ((product?.stock ?: 0) <= 1) {
                            "${product?.stock} stock left"
                        } else {
                            "${product?.stock} stocks left"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = textColorPrimary,
                        maxLines = 1,
                        modifier = Modifier.padding(start = SmallPadding5)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_dots_vertical),
                    contentDescription = "Rating Star",
                    modifier = Modifier.clickable {
                        onClickVerticalDots()
                    }
                )
            }
            Spacer(modifier = Modifier.height(MediumPadding3))
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProductCardPreview() {
    ProductCard(
        modifier = Modifier,
        onClickProductCard = {

        },
        onClickVerticalDots = {

        },
        product = ProductVO(
            title = "",
            price = 1,
            rating = 0.0,
            stock = 1,
            brand = "",
            thumbnail = "",
            images = emptyList()
        )
    )
}