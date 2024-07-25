package uz.abubakir_khakimov.recipes.presentation.extensions

import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout

fun View.setVisibility(visibility: Boolean) {
    val motionLayout = parent as MotionLayout
    motionLayout.constraintSetIds.forEach {
        val constraintSet = motionLayout.getConstraintSet(it) ?: return@forEach
        constraintSet.setVisibility(this.id, if(visibility) View.VISIBLE else View.GONE)
        constraintSet.applyTo(motionLayout)
    }
}