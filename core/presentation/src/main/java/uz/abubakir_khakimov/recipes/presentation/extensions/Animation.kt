package uz.abubakir_khakimov.recipes.presentation.extensions

import android.animation.Animator
import android.view.animation.Animation

const val ANIMATION_START = "animation_start"
const val ANIMATION_END = "animation_end"
const val ANIMATION_REPEAT = "animation_repeat"
const val ANIMATION_CANCEL = "animation_cancel"

fun Animation.setAnimationListener(body: (animation: Animation?, animState: String) -> Unit){
    setAnimationListener(object : Animation.AnimationListener{
        override fun onAnimationStart(animation: Animation?) {
            body(animation, ANIMATION_START)
        }
        override fun onAnimationEnd(animation: Animation?) {
            body(animation, ANIMATION_END)
        }
        override fun onAnimationRepeat(animation: Animation?) {
            body(animation, ANIMATION_REPEAT)
        }
    })
}

fun Animator.setAnimationListener(body: (animator: Animator?, animState: String) -> Unit){
    addListener(object : Animator.AnimatorListener{
        override fun onAnimationStart(animation: Animator) {
            body(animation, ANIMATION_START)
        }
        override fun onAnimationEnd(animation: Animator) {
            body(animation, ANIMATION_END)
        }
        override fun onAnimationCancel(animation: Animator) {
            body(animation, ANIMATION_CANCEL)
        }
        override fun onAnimationRepeat(animation: Animator) {
            body(animation, ANIMATION_REPEAT)
        }
    })
}