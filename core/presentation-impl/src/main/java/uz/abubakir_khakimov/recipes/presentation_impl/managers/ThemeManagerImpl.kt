package uz.abubakir_khakimov.recipes.presentation_impl.managers

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import uz.abubakir_khakimov.recipes.presentation.managers.ResourceManager
import uz.abubakir_khakimov.recipes.presentation.managers.ThemeManager
import uz.abubakir_khakimov.recipes.presentation_impl.R
import uz.abubakir_khakimov.recipes.presentation_impl.domain.repositories.CacheRepository

class ThemeManagerImpl(
    private val cacheRepository: CacheRepository,
    private val resourceManager: ResourceManager
): ThemeManager {

    override fun changeTheme(mode: Int) {
        setSavedTheme(mode = mode)
        setTheme(mode = mode)
    }

    override fun restoreTheme() {
        when (val mode = getSavedTheme()) {
            AppCompatDelegate.MODE_NIGHT_NO, AppCompatDelegate.MODE_NIGHT_YES -> {
                setTheme(mode = mode)
            }
        }
    }

    override fun getCurrentTheme(context: Context): Int =
        context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

    override fun getCurrentThemeTitle(context: Context): String =
        when (getCurrentTheme(context = context)) {
            Configuration.UI_MODE_NIGHT_NO -> resourceManager.getString(resId = R.string.light)
            Configuration.UI_MODE_NIGHT_YES -> resourceManager.getString(resId = R.string.night)
            else -> throw IllegalStateException()
        }

    override fun isLightTheme(context: Context): Boolean =
        when (getCurrentTheme(context = context)) {
            Configuration.UI_MODE_NIGHT_NO -> true //light
            Configuration.UI_MODE_NIGHT_YES -> false //night
            else -> true
        }

    private fun setTheme(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(/* mode = */ mode)
    }

    private fun getSavedTheme(): Int? = cacheRepository
        .getDataFromCache(key = SAVED_THEME_KEY, defaultValue = null)

    private fun setSavedTheme(mode: Int) = cacheRepository
        .saveDataToCache(key = SAVED_THEME_KEY, data = mode)

    companion object {

        const val SAVED_THEME_KEY = "saved_theme_key"
    }
}