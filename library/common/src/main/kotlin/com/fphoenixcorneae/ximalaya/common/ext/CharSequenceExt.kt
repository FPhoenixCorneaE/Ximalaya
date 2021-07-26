package com.fphoenixcorneae.ximalaya.common.ext

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan

/**
 * 文字缩进
 */
fun CharSequence?.indent(spaceCount: Int = 8) = kotlin.run {
    var blankSpace = ""
    for (index in 1..spaceCount) {
        blankSpace += "\u0020"
    }
    val result = SpannableString(blankSpace + this)
    result.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, spaceCount, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    result
}