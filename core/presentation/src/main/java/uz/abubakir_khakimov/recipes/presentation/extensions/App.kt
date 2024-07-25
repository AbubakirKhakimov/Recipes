package uz.abubakir_khakimov.recipes.presentation.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.NameNotFoundException
import androidx.fragment.app.Fragment

const val GOOGLE_PLAY_APP_LINK_TEMPLATE = "https://play.google.com/store/apps/details?id="

fun Fragment.getApplicationName(): String = requireActivity().getApplicationName()

fun Context.getApplicationName(): String {
    val applicationInfo = applicationInfo
    val stringId = applicationInfo.labelRes
    return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString()
    else getString(/* resId = */ stringId)
}

fun Fragment.getVersionName(): String? = requireActivity().getVersionName()

fun Context.getVersionName(): String? = try {
    packageManager.getPackageInfo(/* packageName = */ packageName, /* flags = */ 0).versionName
} catch (e: NameNotFoundException) { null }

fun Context.triggerRebirth() {
    val intent = packageManager.getLaunchIntentForPackage(/* packageName = */ packageName) ?: return
    val mainIntent = Intent.makeRestartActivityTask(/* mainActivity = */ intent.component)
    mainIntent.setPackage(/* packageName = */ packageName)
    startActivity(/* intent = */ mainIntent)
    Runtime.getRuntime().exit(/* status = */ 0)
}

fun Fragment.getGooglePlayAppLink(): String = requireActivity().getGooglePlayAppLink()

fun Context.getGooglePlayAppLink(): String = GOOGLE_PLAY_APP_LINK_TEMPLATE + packageName