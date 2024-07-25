package uz.abubakir_khakimov.recipes.presentation.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import android.webkit.URLUtil
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File
import java.io.FileOutputStream

const val FILE_NAME_IS_NOT_DEFINED = "File name is not defined"

fun getFileNameFromUrl(url: String): String = URLUtil.guessFileName(
    /* url = */ url,
    /* contentDisposition = */ null,
    /* mimeType = */ null
)

fun getMimeTypeFromUrl(url: String): String? = getMimeTypeFromExtension(
    extension = MimeTypeMap.getFileExtensionFromUrl(/* url = */ url)
)

fun getMimeTypeFromExtension(extension: String) = MimeTypeMap.getSingleton()
    .getMimeTypeFromExtension(/* extension = */ extension)

fun Fragment.openFile(file: File, appsNotFoundAction: () -> Unit = {}) =
    requireActivity().openFile(file = file, appsNotFoundAction = appsNotFoundAction)

fun Context.openFile(file: File, appsNotFoundAction: () -> Unit = {}){
    val intent = Intent(/* action = */ Intent.ACTION_VIEW)
    val fileUri = FileProvider.getUriForFile(
        /* context = */ this,
        /* authority = */ applicationContext.packageName + ".provider",
        /* file = */ file
    )
    val mimeType = getMimeTypeFromExtension(extension = file.extension)

    intent.setDataAndType(/* data = */ fileUri,/* type = */ mimeType)
    intent.addFlags(/* flags = */ Intent.FLAG_GRANT_READ_URI_PERMISSION)

    try {
        startActivity(/* intent = */ intent)
    } catch (e: ActivityNotFoundException) {
        appsNotFoundAction()
    }
}

fun Fragment.shareFile(
    file: File,
    extraSubject: String,
    extraText: String,
    appsNotFoundAction: () -> Unit = {}
) = requireActivity().shareFile(
    file = file,
    extraSubject = extraSubject,
    extraText = extraText,
    appsNotFoundAction = appsNotFoundAction
)

fun Context.shareFile(
    file: File,
    extraSubject: String,
    extraText: String,
    appsNotFoundAction: () -> Unit = {}
) {
    val intent = Intent(/* action = */ Intent.ACTION_SEND)
    val fileUri = FileProvider.getUriForFile(
        /* context = */ this,
        /* authority = */ applicationContext.packageName + ".provider",
        /* file = */ file
    )
    val mimeType = getMimeTypeFromExtension(extension = file.extension)

    intent.putExtra(
        /* name = */ Intent.EXTRA_SUBJECT,
        /* value = */ extraSubject
    )
    intent.putExtra(
        /* name = */ Intent.EXTRA_TEXT,
        /* value = */ extraText
    )
    intent.setType(/* type = */ mimeType)
    intent.putExtra(/* name = */ Intent.EXTRA_STREAM, /* value = */ fileUri)
    intent.addFlags(/* flags = */ Intent.FLAG_GRANT_READ_URI_PERMISSION)

    try {
        startActivity(/* intent = */ intent)
    } catch (e: ActivityNotFoundException) {
        appsNotFoundAction()
    }
}

fun Fragment.openLink(linkUrl: String) = requireActivity().openLink(linkUrl = linkUrl)

fun Context.openLink(linkUrl: String) = Intent(
    /* action = */ Intent.ACTION_VIEW,
    /* uri = */ Uri.parse(/* uriString = */ linkUrl)
).let { startActivity(/* intent = */ it) }

fun Context.getFileName(uri: Uri): String? {
    var result: String? = null

    if (uri.scheme == "content") {
        val cursor = contentResolver.query(
            /* uri = */ uri,
            /* projection = */ null,
            /* selection = */ null,
            /* selectionArgs = */ null,
            /* sortOrder = */ null
        )
        cursor.use {
            if (it != null && it.moveToFirst()) {
                result = it.getString(
                    /* columnIndex = */ it.getColumnIndexOrThrow(
                        /* columnName = */ OpenableColumns.DISPLAY_NAME
                    )
                )
            }
        }
    }

    if (result == null) {
        val cutStartIndex = uri.path?.lastIndexOf(char = '/') ?: -1
        if (cutStartIndex != -1) result = uri.path?.substring(startIndex = cutStartIndex + 1)
    }

    return result
}

fun Fragment.copyFileToInternalStorage(uri: Uri): File =
    requireActivity().copyFileToInternalStorage(uri = uri)

fun Context.copyFileToInternalStorage(uri: Uri): File {
    val inputStream = contentResolver?.openInputStream(/* uri = */ uri)
    val file = File(
        /* parent = */ filesDir, /* child = */ getFileName(uri = uri) ?: FILE_NAME_IS_NOT_DEFINED
    )
    val fileOutputStream = FileOutputStream(/* file = */ file)

    inputStream?.copyTo(out = fileOutputStream)

    inputStream?.close()
    fileOutputStream.close()

    return file
}