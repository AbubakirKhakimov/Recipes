package uz.abubakir_khakimov.recipes.presentation.managers

import android.content.Context

interface ThemeManager {

    fun changeTheme(mode: Int)

    fun restoreTheme()

    fun getCurrentTheme(context: Context): Int

    fun getCurrentThemeTitle(context: Context): String

    fun isLightTheme(context: Context): Boolean
}