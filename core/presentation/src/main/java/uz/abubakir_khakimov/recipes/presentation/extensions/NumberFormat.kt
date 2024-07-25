package uz.abubakir_khakimov.recipes.presentation.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.format(locale: Locale = Locale.getDefault()): String =
    NumberFormat.getInstance(/* inLocale = */ locale)
        .apply { isGroupingUsed = false }
        .format(/* number = */ this)
        .replace(oldValue = ",", newValue = ".")

fun Double.formatWithGrouping(locale: Locale = Locale.getDefault()): String =
    NumberFormat.getInstance(/* inLocale = */ locale)
        .apply { isGroupingUsed = true }
        .format(/* number = */ this)

fun Float.format(locale: Locale = Locale.getDefault()): String =
    NumberFormat.getInstance(/* inLocale = */ locale)
        .apply { isGroupingUsed = false }
        .format(/* obj = */ this)
        .replace(oldValue = ",", newValue = ".")

fun Float.formatWithGrouping(locale: Locale = Locale.getDefault()): String =
    NumberFormat.getInstance(/* inLocale = */ locale)
        .apply { isGroupingUsed = true }
        .format(/* obj = */ this)