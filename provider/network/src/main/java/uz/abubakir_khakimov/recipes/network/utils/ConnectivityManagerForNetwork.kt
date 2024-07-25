package uz.abubakir_khakimov.recipes.network.utils

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Retrofit
import uz.abubakir_khakimov.recipes.common.exceptions.NotInternetConnectionException
import uz.abubakir_khakimov.recipes.common.exceptions.ServerHttpException
import uz.abubakir_khakimov.recipes.common.exceptions.TimeoutException
import uz.abubakir_khakimov.recipes.common.managers.CheckNetworkManager
import uz.abubakir_khakimov.recipes.common.managers.Logger
import uz.abubakir_khakimov.recipes.common.utils.Result
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ConnectivityManagerForNetwork @Inject constructor (
    private val retrofit: Retrofit,
    private val logger: Logger,
    private val checkNetworkManager: CheckNetworkManager
) {

    fun <T> error(throwable: Throwable, message: String? = null): Result<T> =
        Result.error(
            error = checkConnectionError(throwable = throwable),
            message = message
        )

    fun checkConnectionError(throwable: Throwable): Throwable = when {
        throwable is HttpException -> {
            val errorMassage = parseError(throwable.response()?.errorBody())

            logger.showLogD(throwable.response().toString())
            logger.showLogE(errorMassage.toString())

            ServerHttpException(
                code = throwable.response()?.code() ?: 0,
                errorMessage = errorMassage
            )
        }

        throwable is SocketTimeoutException || throwable is InterruptedIOException || throwable is TimeoutException -> {
            TimeoutException()
        }

        !checkNetworkManager.isNetworkConnected() -> {
            NotInternetConnectionException()
        }

        else -> {
            logger.showLogError(throwable, "Response error: ")

            throwable
        }
    }

    private fun parseError(errorBody: ResponseBody?): String? = try {
        if (errorBody == null) {
            null
        } else {
            val converter: Converter<ResponseBody, Any> = retrofit
                .responseBodyConverter(
                    /* type = */ Any::class.java,
                    /* annotations = */ arrayOfNulls<Annotation>(0)
                )
            converter.convert(errorBody)!!.toString()
        }
    } catch (e: Exception) {
        null
    }
}