package uz.abubakir_khakimov.recipes.presentation_impl.managers

import android.content.Context
import android.view.Gravity
import com.google.android.material.button.MaterialButton
import uz.abubakir_khakimov.recipes.common.exceptions.NotInternetConnectionException
import uz.abubakir_khakimov.recipes.common.exceptions.ServerHttpException
import uz.abubakir_khakimov.recipes.common.exceptions.TimeoutException
import uz.abubakir_khakimov.recipes.presentation.extensions.triggerRebirth
import uz.abubakir_khakimov.recipes.presentation.managers.ConnectivityManager
import uz.abubakir_khakimov.recipes.presentation.managers.HighPriorityBannerManager
import uz.abubakir_khakimov.recipes.presentation.managers.ToastManager
import uz.abubakir_khakimov.recipes.presentation_impl.R

class ConnectivityManagerImpl(
    private val context: Context,
    private val toastManager: ToastManager,
    private val highPriorityBannerManager: HighPriorityBannerManager
): ConnectivityManager {

    private fun showNoConnectionBanner() = highPriorityBannerManager.apply {
        setLayout(layoutRes = R.layout.no_connected_banner_layout)
        setFocusable(focusable = false)
        setAutoDismissEnable(enable = true)
        setAutoDismissDuration(duration = HighPriorityBannerManager.DEFAULT_BANNER_DURATION)
        show(gravity = Gravity.TOP)
    }.let {}

    override fun checkConnectionError(
        throwable: Throwable?,
        autoHandleNoConnection: Boolean
    ) = when (throwable) {
        is ServerHttpException -> isServerHttpException(
            serverHttpException = throwable
        )
        is TimeoutException -> toastManager.getStringAndShowToast(resId = R.string.connection_timeout)
        is NotInternetConnectionException -> if (autoHandleNoConnection) showNoConnectionBanner() else Unit
        else -> {
            throwable?.printStackTrace()
            toastManager.getStringAndShowToast(resId = R.string.error)
        }
    }

    private fun isServerHttpException(
        serverHttpException: ServerHttpException
    ) = when {
        serverHttpException.errorMessage == null -> toastManager.getStringAndShowToast(
            resId = R.string.responseError
        )
        else -> toastManager.getStringAndShowToast(
            R.string.error_s, serverHttpException.errorMessage!!
        )
    }
}