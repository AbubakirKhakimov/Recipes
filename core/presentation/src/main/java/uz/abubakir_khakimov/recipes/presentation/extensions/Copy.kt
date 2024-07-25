package uz.abubakir_khakimov.recipes.presentation.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.copyToClipboard(clipLabel: String, text: CharSequence) =
    requireContext().copyToClipboard(clipLabel = clipLabel, text = text)

fun Context.copyToClipboard(clipLabel: String, text: CharSequence) =
    ContextCompat.getSystemService(
        /* context = */ this,
        /* serviceClass = */ ClipboardManager::class.java
    )?.setPrimaryClip(
        /* clip = */ ClipData.newPlainText(/* label = */ clipLabel, /* text = */text)
    )