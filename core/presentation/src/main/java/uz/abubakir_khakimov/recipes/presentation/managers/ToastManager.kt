package uz.abubakir_khakimov.recipes.presentation.managers

interface ToastManager {

    fun toast(message: String)

    fun getStringAndShowToast(resId: Int)

    fun getStringAndShowToast(resId: Int, vararg formatArgs: Any)
}