package uz.abubakir_khakimov.recipes.presentation.extensions

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

fun Context.createConfigurationContextByLocale(locale: Locale): Context =
    Configuration().let {
        it.setLocale(/* loc = */ locale)
        createConfigurationContext(/* overrideConfiguration = */ it)
    }
