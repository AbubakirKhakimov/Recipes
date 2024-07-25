package uz.abubakir_khakimov.recipes.presentation_impl.managers

import android.content.Context
import android.widget.Toast
import uz.abubakir_khakimov.recipes.presentation.managers.ResourceManager
import uz.abubakir_khakimov.recipes.presentation.managers.ToastManager

class ToastManagerImpl(
    private val context: Context,
    private val resourceManager: ResourceManager
): ToastManager {

    override fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun String.showToast() = toast(this)

    override fun getStringAndShowToast(resId: Int){
        resourceManager.getString(resId).showToast()
    }

    override fun getStringAndShowToast(resId: Int, vararg formatArgs: Any){
        resourceManager.getString(resId, *formatArgs).showToast()
    }
}