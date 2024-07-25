package uz.abubakir_khakimov.recipes.presentation.extensions

import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.annotation.ColorRes

fun TextView.paintDefinitePartOfText(
    partOfText: String,
    afterThat: String? = null,
    @ColorRes colorRes: Int
){
    val spannable = text as Spannable
    val afterThatIndex = afterThat?.let { text.indexOf(string = it) }
        .let { if (it == -1) null else it }

    val partStartIndex = if (afterThatIndex == null) text.indexOf(string = partOfText)
    else text.indexOf(string = partOfText, startIndex = afterThatIndex)
        .also { if (it == -1) return }
    val partEndIndex = partStartIndex + partOfText.length

    spannable.setSpan(
        /* what = */ ForegroundColorSpan(/* color = */ context.getColorCompat(colorRes = colorRes)),
        /* start = */ partStartIndex,
        /* end = */ partEndIndex,
        /* flags = */ Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}

fun TextView.paintDefinitePartOfTextLast(
    partOfText: String,
    afterThat: String? = null,
    @ColorRes colorRes: Int
){
    val spannable = text as Spannable
    val afterThatIndex = afterThat?.let { text.lastIndexOf(string = it) }
        .let { if (it == -1) null else it }

    val partStartIndex = if (afterThatIndex == null) text.lastIndexOf(string = partOfText)
    else text.lastIndexOf(string = partOfText, startIndex = afterThatIndex)
        .also { if (it == -1) return }
    val partEndIndex = partStartIndex + partOfText.length

    spannable.setSpan(
        /* what = */ ForegroundColorSpan(/* color = */ context.getColorCompat(colorRes = colorRes)),
        /* start = */ partStartIndex,
        /* end = */ partEndIndex,
        /* flags = */ Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}