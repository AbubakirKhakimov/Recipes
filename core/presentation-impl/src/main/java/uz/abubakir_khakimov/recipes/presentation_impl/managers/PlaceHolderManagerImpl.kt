package uz.abubakir_khakimov.recipes.presentation_impl.managers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import uz.abubakir_khakimov.recipes.presentation.managers.PlaceHolderManager
import uz.abubakir_khakimov.recipes.presentation.managers.ScreenManager

class PlaceHolderManagerImpl(
    private val screenManager: ScreenManager
): PlaceHolderManager {

    override fun createByCustomCount(
        context: Context,
        itemResource: Int,
        parentLayout: LinearLayout,
        beforeCleanUp: Boolean,
        placeHolderCount: Int,
        layoutParams: PlaceHolderManager.LayoutParams
    ) {
        if (beforeCleanUp) parentLayout.removeAllViews()
        parentLayout.setLayoutParams(layoutParams = layoutParams)

        createPlaceHoldersByCustomCount(
            context = context,
            itemResource = itemResource,
            parentLayout = parentLayout,
            placeHolderCount = placeHolderCount
        )
    }

    private fun createPlaceHoldersByCustomCount(
        context: Context,
        itemResource: Int,
        parentLayout: LinearLayout,
        placeHolderCount: Int
    ) = repeat(times = placeHolderCount) {
        val itemView = createViewByResource(
            context = context,
            resource = itemResource,
            parentLayout = parentLayout
        )
        parentLayout.addView(/* child = */ itemView)
    }

    override fun createByScreenSize(
        context: Context,
        itemResource: Int,
        parentLayout: LinearLayout,
        beforeCleanUp: Boolean,
        layoutParams: PlaceHolderManager.LayoutParams
    ) {
        if (beforeCleanUp) parentLayout.removeAllViews()
        parentLayout.setLayoutParams(layoutParams = layoutParams)

        createPlaceHoldersByScreenSize(
            context = context,
            itemResource = itemResource,
            parentLayout = parentLayout
        )
    }

    private fun createPlaceHoldersByScreenSize(
        context: Context,
        itemResource: Int,
        parentLayout: LinearLayout
    ) {
        var numberOfItems = 1
        var index = 0

        do{
            val itemView = createViewByResource(
                context = context,
                resource = itemResource,
                parentLayout = parentLayout
            )
            parentLayout.addView(/* child = */ itemView)

            if (index == 0) numberOfItems = calculateNumberOfItems(
                itemView = itemView,
                orientation = parentLayout.orientation
            )

            index++
        }while (index <= numberOfItems)
    }

    private fun calculateNumberOfItems(
        itemView: View,
        orientation: Int
    ): Int = when (orientation) {
        LinearLayout.VERTICAL -> screenManager.getScreenHeight() / getItemHeight(itemView = itemView)
        LinearLayout.HORIZONTAL -> screenManager.getScreenWidth() / getItemWidth(itemView = itemView)
        else -> throw IllegalArgumentException(INVALID_ORIENTATION_ARGUMENT_MESSAGE, null)
    }

    private fun getItemHeight(itemView: View): Int =
        measureItem(itemView = itemView).measuredHeight

    private fun getItemWidth(itemView: View): Int =
        measureItem(itemView = itemView).measuredWidth

    private fun measureItem(itemView: View): View{
        val wrapContentSpec = View.MeasureSpec.makeMeasureSpec(
            /* size = */ View.MeasureSpec.UNSPECIFIED,
            /* mode = */View.MeasureSpec.UNSPECIFIED
        )
        itemView.measure(
            /* widthMeasureSpec = */ wrapContentSpec,
            /* heightMeasureSpec = */wrapContentSpec
        )
        return itemView
    }

    private fun createViewByResource(
        context: Context,
        resource: Int,
        parentLayout: LinearLayout
    ): View = LayoutInflater
        .from(/* context = */ context)
        .inflate(/* resource = */ resource, /* root = */ parentLayout, /* attachToRoot = */ false)

    private fun LinearLayout.setLayoutParams(layoutParams: PlaceHolderManager.LayoutParams) =
        setPadding(
            /* left = */ layoutParams.paddingStart,
            /* top = */ layoutParams.paddingTop,
            /* right = */ layoutParams.paddingEnd,
            /* bottom = */ layoutParams.paddingBottom
        )

    companion object{

        private const val INVALID_ORIENTATION_ARGUMENT_MESSAGE =
            "This orientation argument is invalid. " +
                    "The Orientation argument accepts only " +
                    "LinearLayout.VERTICAL or LinearLayout.HORIZONTAL values."
    }
}