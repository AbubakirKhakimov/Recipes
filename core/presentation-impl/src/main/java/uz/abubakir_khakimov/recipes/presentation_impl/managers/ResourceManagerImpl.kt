package uz.abubakir_khakimov.recipes.presentation_impl.managers

import android.content.Context
import uz.abubakir_khakimov.recipes.presentation.extensions.createConfigurationContextByLocale
import uz.abubakir_khakimov.recipes.presentation.managers.ResourceManager
import java.util.Locale

class ResourceManagerImpl(
    private val applicationContext: Context
): ResourceManager {

    private val contextWithLocale = applicationContext.createConfigurationContextByLocale(
        locale = Locale.getDefault()
    )

    override fun getString(resId: Int): String = contextWithLocale.getString(resId)

    override fun getString(resId: Int, vararg formatArgs: Any): String =
        contextWithLocale.getString(resId, *formatArgs)
}