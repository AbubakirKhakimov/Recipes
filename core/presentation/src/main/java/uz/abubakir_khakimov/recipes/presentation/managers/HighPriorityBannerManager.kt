package uz.abubakir_khakimov.recipes.presentation.managers

import android.view.View
import android.widget.PopupWindow

interface HighPriorityBannerManager {

    fun setLayout(layoutRes: Int)

    fun setAutoDismissDuration(duration: Long)

    fun setAutoDismissEnable(enable: Boolean)

    fun setFocusable(focusable: Boolean)

    fun getContentView(): View

    fun setTextViewId(resId: Int)

    fun setText(text: String)

    fun setTextAnd(text: String): HighPriorityBannerManager

    fun show(gravity: Int)

    fun show(gravity: Int, x: Int, y: Int)

    fun show(anchorView: View, gravity: Int)

    fun show(anchorView: View, gravity: Int, x: Int, y: Int)

    fun dismiss()

    fun setOnDismissListener(onDismissListener: PopupWindow.OnDismissListener)

    fun setOnDismissListener(body: () -> Unit)

    companion object{

        const val DEFAULT_BANNER_DURATION = 6000L
        const val SHORT_BANNER_DURATION = 4000L
    }
}