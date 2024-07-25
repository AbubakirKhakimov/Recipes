package uz.abubakir_khakimov.recipes.presentation.extensions

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable

fun Fragment.stringArgs(key: String): Lazy<String?> = lazy {
    requireArguments().getString(
        /* key = */ key
    )
}

fun Fragment.stringArgs(key: String, defaultValue: String?): Lazy<String?> = lazy {
    requireArguments().getString(
        /* key = */ key,
        /* defaultValue = */ defaultValue
    )
}

fun Fragment.intArgs(key: String): Lazy<Int> = lazy {
    requireArguments().getInt(
        /* key = */ key
    )
}

fun Fragment.intArgs(key: String, defaultValue: Int): Lazy<Int> = lazy {
    requireArguments().getInt(
        /* key = */ key,
        /* defaultValue = */ defaultValue
    )
}

fun Fragment.booleanArgs(key: String): Lazy<Boolean> = lazy {
    requireArguments().getBoolean(
        /* key = */ key
    )
}

fun Fragment.booleanArgs(key: String, defaultValue: Boolean): Lazy<Boolean> = lazy {
    requireArguments().getBoolean(
        /* key = */ key,
        /* defaultValue = */ defaultValue
    )
}

inline fun <reified T: Serializable> Fragment.serializableArgs(key: String): Lazy<T?> =
    lazy { requireArguments().extractSerializable(key = key) }

inline fun <reified T : Parcelable> Fragment.parcelableArrayListArgs(key: String): Lazy<List<T>?> =
    lazy { requireArguments().extractParcelableArrayList(key = key) }

inline fun <reified T : Serializable> Bundle.extractSerializable(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(/* key = */ key, /* clazz = */ T::class.java)
    } else {
        getSerializable(/* key = */ key) as T
    }

inline fun <reified T : Parcelable> Bundle.extractParcelableArrayList(key: String): List<T>? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayList(/* key = */ key, /* clazz = */ T::class.java)
    } else {
        getParcelableArrayList(/* key = */ key)
    }