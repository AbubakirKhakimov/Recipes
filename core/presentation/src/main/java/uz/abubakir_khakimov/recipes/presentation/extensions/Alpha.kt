package uz.abubakir_khakimov.recipes.presentation.extensions

import kotlin.math.roundToInt

val Float.intAlpha: Int
    get() = (this * 255).roundToInt()