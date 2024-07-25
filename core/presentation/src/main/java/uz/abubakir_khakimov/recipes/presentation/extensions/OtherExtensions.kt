package uz.abubakir_khakimov.recipes.presentation.extensions

import java.text.DecimalFormat

fun checkLoadedAllData(dataList: List<Any?>): Boolean {
    dataList.forEach {
        if (it == null) return false
    }

    return true
}

fun getRoundedNumber(number: Double): String {
    return DecimalFormat("#.##")
        .format(number)
        .replace(",", ".")
}

fun String.removeSpaces() = replace(" ", "")

fun String.firstUppercase() = substring(startIndex = 0, endIndex = 1)
    .uppercase()
    .plus(other = substring(startIndex = 1))