package uz.abubakir_khakimov.recipes.presentation.extensions

import android.animation.ValueAnimator
import android.view.View

private const val FULL_ALPHA = 1f
private const val DIMMER_ALPHA = 0.8f

fun View.animateOnDimmer(){
    val valueAnimator = ValueAnimator.ofFloat(/* ...values = */ FULL_ALPHA, DIMMER_ALPHA)
    valueAnimator.addUpdateListener {
        alpha = it.animatedValue as Float
    }
    valueAnimator.start()
}

fun View.animateOffDimmer(){
    val valueAnimator = ValueAnimator.ofFloat(/* ...values = */ DIMMER_ALPHA, FULL_ALPHA)
    valueAnimator.addUpdateListener {
        alpha = it.animatedValue as Float
    }
    valueAnimator.start()
}