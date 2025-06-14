package com.gena_korobeynikov.yandexfinance.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

import com.gena_korobeynikov.yandexfinance.R
import java.lang.reflect.Field

object EmojiMapper {

    // Если понадобится точное соответствие макету фигме
    @DrawableRes
    fun getEmoji(name: String): Int  {
        return when (name.lowercase()) {
            "🐶" -> R.drawable.emoji__dog_face
            //...


            else -> { R.drawable.ic_launcher_foreground}
        }
}
    }