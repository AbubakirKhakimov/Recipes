package uz.abubakir_khakimov.recipes.presentation.extensions

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

fun String.toRequestBody(): RequestBody = toRequestBody(
    contentType = "text/plain".toMediaTypeOrNull()
)

fun Int.toRequestBody(): RequestBody = toString().toRequestBody(
    contentType = "Integer/plain".toMediaTypeOrNull()
)

fun Double.toRequestBody(): RequestBody = toString().toRequestBody(
    contentType = "Number/plain".toMediaTypeOrNull()
)

fun File.toMultipartBodyPart(columnName: String): MultipartBody.Part =
    MultipartBody.Part.createFormData(
        name = columnName,
        filename = name,
        body = asRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull())
    )