package uz.abubakir_khakimov.recipes.presentation_impl.managers

import android.content.Context
import android.content.res.Configuration
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.ChecksSdkIntAtLeast
import uz.abubakir_khakimov.recipes.presentation.managers.ScreenManager


class ScreenManagerImpl(
    private val context: Context
): ScreenManager {

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    private val isVersionLatest = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
    private val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    override fun getScreenWidth(): Int = if (isVersionLatest) {
        val windowMetrics = windowManager.currentWindowMetrics
        val bounds = windowMetrics.bounds
        val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(
            WindowInsets.Type.systemBars()
        )
        if (context.resources.configuration.orientation
            == Configuration.ORIENTATION_LANDSCAPE
            && context.resources.configuration.smallestScreenWidthDp < 600
        ) { // landscape and phone
            val navigationBarSize = insets.right + insets.left
            bounds.width() - navigationBarSize
        } else { // portrait or tablet
            bounds.width()
        }
    } else {
        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        outMetrics.widthPixels
    }

    override fun getScreenHeight(): Int = if (isVersionLatest) {
        val windowMetrics = windowManager.currentWindowMetrics
        val bounds = windowMetrics.bounds
        val insets: Insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(
            WindowInsets.Type.systemBars()
        )
        if (context.resources.configuration.orientation
            == Configuration.ORIENTATION_LANDSCAPE
            && context.resources.configuration.smallestScreenWidthDp < 600
        ) { // landscape and phone
            bounds.height()
        } else { // portrait or tablet
            val navigationBarSize: Int = insets.bottom
            bounds.height() - navigationBarSize
        }
    } else {
        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        outMetrics.heightPixels
    }
}