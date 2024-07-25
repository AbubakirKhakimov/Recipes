package uz.abubakir_khakimov.recipes.presentation.managers

interface ConnectivityManager {

    fun checkConnectionError(
        throwable: Throwable?,
        autoHandleNoConnection: Boolean = true
    )
}