package uz.abubakir_khakimov.recipes.common_impl

import android.util.Log
import uz.abubakir_khakimov.recipes.common.managers.Logger

class LoggerImpl: Logger {

    override fun showLogD(message: String) {
        Log.d(TAG, message)
    }

    override fun showLogE(message: String) {
        Log.e(TAG, message)
    }

    override fun showLogError(throwable: Throwable, message: String?) {
        Log.e(TAG, message, throwable)
    }

    companion object{
        const val TAG = "AppLogTag"
    }
}