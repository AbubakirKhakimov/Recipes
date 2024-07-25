package uz.abubakir_khakimov.recipes.presentation.managers

import android.content.Context
import java.util.Locale

interface LocaleManager {

    fun changeLocale(localeTag: String)

    fun getCurrentLocale(context: Context): Locale

    fun getCurrentLocaleTag(context: Context): String

    fun getCurrentLocaleFromCache(): Locale

    fun getCurrentLocaleTagFromCache(): String

    fun getCurrentLocaleTitle(context: Context): String

    fun isCurrentLocaleTag(context: Context, localeTag: String): Boolean

    fun isCurrentLocale(context: Context, locale: Locale): Boolean

    fun restoreDefaultLocale(context: Context)

    fun restoreDefaultLocaleByCache()

    companion object{

        const val ENGLISH_LOCALE_TAG = "en-US"
        const val RUSSIAN_LOCALE_TAG = "ru-RU"
        const val UZBEK_LOCALE_TAG = "uz-UZ"
    }
}