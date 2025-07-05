package com.gena_korobeynikov.yandexfinance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gena_korobeynikov.yandexfinance.R


fun getInitialsFromTitle(title: String): String {
    return title.split(" ").take(2).joinToString("") { it.take(1).uppercase() }
}

// Можно было взять готовый, но первый раз с compose работаю, поэтоу ради практики
@Composable
fun MainListItem(
    modifier: Modifier = Modifier,
    mainText: String,
    subtitle: String? = null,
    emoji: String? = null,
    trailing: (@Composable () -> Unit)? = null,
    color: Color? = null,
    huggingHeight: Boolean = false,
    emojiWhiteBg : Boolean = false,
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (huggingHeight) Modifier.defaultMinSize(minHeight = 56.dp)
                else Modifier.height(70.dp)
            ),
        shape = RectangleShape,
        colors =  if (color != null) CardDefaults.cardColors(containerColor = color)
                else CardDefaults.cardColors(containerColor = colorResource(id= R.color.surface))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical =8.dp),
            verticalAlignment = Alignment.CenterVertically,


        ) {
            // Lead
            if (emoji != null) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = if (emojiWhiteBg) Color.White else colorResource(id = R.color.secondary_green),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                        if (emoji == "letters") {
                           Text(
                               text = getInitialsFromTitle(mainText),
                               fontSize = 10.sp,
                               fontFamily = FontFamily.Default,
                               fontWeight = FontWeight.Medium,
                               color = Color(0xFF1D1B20)
                           )
                        }

                        else {
                            Text(
                                text = emoji,
                                fontSize = 16.sp,
                            )
                        }

                }

                Spacer(modifier = Modifier.width(16.dp))
            }

            // Content
            Column(
                modifier = Modifier.weight(1f)
                    .then(
                        if (huggingHeight) Modifier.wrapContentHeight()
                        else Modifier.fillMaxHeight()
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = if (huggingHeight) Modifier.height(40.dp).wrapContentHeight(align = Alignment.CenterVertically) else Modifier,
                    text = mainText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorResource(id = R.color.on_surface),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color= colorResource(id= R.color.on_surface_variant),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                }
            }

            // Trail
            trailing?.invoke()
        }
    }
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 1.dp,
        color = colorResource(id = R.color.outline_variant)
    )

}


@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun MainListItemPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            // Простой вариант
            MainListItem(
                mainText = "Заголовок элемента",
                subtitle = "Подзаголовок элемента"
            )

            // С emoji
            MainListItem(
                mainText = "Элемент с emoji",
                subtitle = "и заголовком",
                emoji = "🍭"
            )

            // С буквами
            MainListItem(
                mainText = "Иван Петров",
                emoji = "letters"
            )

            // С кастомным цветом
            MainListItem(
                huggingHeight = true,
                mainText = "Элемент с цветом",
                color = colorResource(id = R.color.secondary_green),
                trailing = {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null
                    )
                }
            )

        }
    }
}



