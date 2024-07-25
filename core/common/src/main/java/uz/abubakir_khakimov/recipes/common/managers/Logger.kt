package uz.abubakir_khakimov.recipes.common.managers

interface Logger {

    fun showLogD(message: String)

    fun showLogE(message: String)

    fun showLogError(throwable: Throwable, message: String? = null)
}