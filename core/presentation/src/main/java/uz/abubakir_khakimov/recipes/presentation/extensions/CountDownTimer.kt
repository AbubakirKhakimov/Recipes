package uz.abubakir_khakimov.recipes.presentation.extensions

import android.os.CountDownTimer
import androidx.fragment.app.Fragment

fun countDownTimer(
    millisInFuture: Long,
    countDownInterval: Long,
    onTick: (millisUntilFinished: Long) -> Unit,
    onFinish: () -> Unit
): CountDownTimer = object : CountDownTimer(
    /* millisInFuture = */ millisInFuture,
    /* countDownInterval = */ countDownInterval
) {
    override fun onTick(millisUntilFinished: Long) = onTick(millisUntilFinished)
    override fun onFinish() = onFinish()
}