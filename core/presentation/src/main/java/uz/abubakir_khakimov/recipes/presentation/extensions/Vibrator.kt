package uz.abubakir_khakimov.recipes.presentation.extensions

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment

fun Fragment.vibrate(milliseconds: Long, amplitude: Int = VibrationEffect.DEFAULT_AMPLITUDE) =
    requireContext().vibrate(milliseconds = milliseconds, amplitude = amplitude)

fun Context.vibrate(milliseconds: Long, amplitude: Int = VibrationEffect.DEFAULT_AMPLITUDE) =
    getSystemService(/* serviceClass = */ Vibrator::class.java).vibrate(
        /* vibe = */ VibrationEffect.createOneShot(
            /* milliseconds = */ milliseconds,
            /* amplitude = */ amplitude
        )
    )