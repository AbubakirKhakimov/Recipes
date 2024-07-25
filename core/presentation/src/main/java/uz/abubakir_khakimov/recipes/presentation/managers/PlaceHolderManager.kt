package uz.abubakir_khakimov.recipes.presentation.managers

import android.content.Context
import android.widget.LinearLayout

interface PlaceHolderManager {

    fun createByScreenSize(
        context: Context,
        itemResource: Int,
        parentLayout: LinearLayout,
        beforeCleanUp: Boolean,
        layoutParams: LayoutParams = LayoutParams()
    )

    fun createByCustomCount(
        context: Context,
        itemResource: Int,
        parentLayout: LinearLayout,
        beforeCleanUp: Boolean,
        placeHolderCount: Int,
        layoutParams: LayoutParams = LayoutParams()
    )

    data class LayoutParams(
        val paddingTop: Int = 0, //by pixels
        val paddingBottom: Int = 0, //by pixels
        val paddingStart: Int = 0, //by pixels
        val paddingEnd: Int = 0, //by pixels
    )
}