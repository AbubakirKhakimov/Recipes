package uz.abubakir_khakimov.recipes.presentation.managers

interface ResourceManager {

    fun getString(resId: Int): String

    fun getString(resId: Int, vararg formatArgs: Any): String

    companion object{

        const val FOR_WIDGETS = "for_widgets"
    }
}