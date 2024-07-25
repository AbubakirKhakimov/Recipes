package uz.abubakir_khakimov.recipes.presentation.extensions

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.RemoteViews


const val SET_ENABLED_METHOD_NAME = "setEnabled"

fun extractAppWidgetId(intent: Intent?): Int = intent?.getIntExtra(
    /* name = */ AppWidgetManager.EXTRA_APPWIDGET_ID,
    /* defaultValue = */ AppWidgetManager.INVALID_APPWIDGET_ID
) ?: AppWidgetManager.INVALID_APPWIDGET_ID

fun getCellCountByDip(dip: Int): Int = dip.plus(30) / 70

fun RemoteViews.setEnabled(viewId: Int, enabled: Boolean) = setBoolean(
    /* viewId = */ viewId,
    /* methodName = */ SET_ENABLED_METHOD_NAME,
    /* value = */ enabled
)

fun RemoteViews.setVisibility(viewId: Int, visibility: Boolean) = setViewVisibility(
    /* viewId = */ viewId,
    /* visibility = */ if (visibility) View.VISIBLE else View.GONE
)

fun getTotalWidgetsCount(context: Context): Int {
    val appWidgetManager = AppWidgetManager.getInstance(/* context = */ context)
    val providersInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        appWidgetManager.getInstalledProvidersForPackage(
            /* packageName = */ context.packageName,
            /* profile = */ null
        ) else appWidgetManager.installedProviders

    var count = 0

    providersInfo.forEach { providerInfo ->
        count += appWidgetManager.getAppWidgetIds(/* provider = */ providerInfo.provider).size
    }

    return count
}