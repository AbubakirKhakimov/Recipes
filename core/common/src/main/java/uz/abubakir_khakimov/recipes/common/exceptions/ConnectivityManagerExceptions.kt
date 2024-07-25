package uz.abubakir_khakimov.recipes.common.exceptions

class ServerHttpException(val code: Int, val errorMessage: String?): Throwable()

class TimeoutException: Throwable()

class NotInternetConnectionException: Throwable()