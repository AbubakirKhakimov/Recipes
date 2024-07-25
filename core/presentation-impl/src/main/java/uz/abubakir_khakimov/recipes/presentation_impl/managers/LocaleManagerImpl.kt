package uz.abubakir_khakimov.recipes.presentation_impl.managers

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import uz.abubakir_khakimov.recipes.presentation.managers.LocaleManager
import uz.abubakir_khakimov.recipes.presentation.managers.LocaleManager.Companion.RUSSIAN_LOCALE_TAG
import uz.abubakir_khakimov.recipes.presentation.managers.LocaleManager.Companion.UZBEK_LOCALE_TAG
import uz.abubakir_khakimov.recipes.presentation.managers.ResourceManager
import uz.abubakir_khakimov.recipes.presentation_impl.R
import uz.abubakir_khakimov.recipes.presentation_impl.domain.repositories.CacheRepository
import java.util.Locale

class LocaleManagerImpl(
    private val resourceManager: ResourceManager,
    private val cacheRepository: CacheRepository
): LocaleManager {

    override fun changeLocale(localeTag: String) {
        val newLocale = Locale.forLanguageTag(/* languageTag = */ localeTag)
        val appLocale = LocaleListCompat.create(/* ...localeList = */ newLocale)
        AppCompatDelegate.setApplicationLocales(/* locales = */ appLocale)

        setDefaultLocale(locale = newLocale)
        saveLocaleTagToCache(localeTag = localeTag)
    }

    override fun getCurrentLocale(context: Context): Locale =
        context.resources.configuration.locales[0]

    override fun getCurrentLocaleTag(context: Context): String =
        getCurrentLocale(context = context).toLanguageTag()

    override fun getCurrentLocaleFromCache(): Locale = cacheRepository.getDataFromCache(
        key = APP_LOCALE_KEY, defaultValue = Locale.getDefault().toLanguageTag()
    ).let { Locale.forLanguageTag(/* languageTag = */ it) }

    override fun getCurrentLocaleTagFromCache(): String = cacheRepository.getDataFromCache(
        key = APP_LOCALE_KEY, defaultValue = Locale.getDefault().toLanguageTag()
    )

    override fun getCurrentLocaleTitle(context: Context): String = when {
        isCurrentLocaleTag(context = context, localeTag = RUSSIAN_LOCALE_TAG) ->
            resourceManager.getString(resId = R.string.russian)

        isCurrentLocaleTag(context = context, localeTag = UZBEK_LOCALE_TAG) ->
            resourceManager.getString(resId = R.string.uzbek)

        else -> resourceManager.getString(resId = R.string.english)
    }

    override fun isCurrentLocaleTag(context: Context, localeTag: String): Boolean = isCurrentLocale(
        context = context,
        locale = Locale.forLanguageTag(/* languageTag = */ localeTag)
    )

    override fun isCurrentLocale(context: Context, locale: Locale): Boolean =
        locale.toLanguageTag() == getCurrentLocaleTag(context = context)

    override fun restoreDefaultLocale(context: Context) = setDefaultLocale(
        locale = getCurrentLocale(context = context)
    )

    override fun restoreDefaultLocaleByCache() = setDefaultLocale(
        locale = getCurrentLocaleFromCache()
    )

    private fun setDefaultLocale(locale: Locale) = Locale.setDefault(/* newLocale = */ locale)

    private fun saveLocaleTagToCache(localeTag: String) = cacheRepository.saveDataToCache(
        key = APP_LOCALE_KEY, data = localeTag
    )

    companion object{

        private const val APP_LOCALE_KEY = "app_locale_key"
    }
}