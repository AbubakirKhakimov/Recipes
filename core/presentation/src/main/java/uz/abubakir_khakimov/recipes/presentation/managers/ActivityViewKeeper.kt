package uz.abubakir_khakimov.recipes.presentation.managers

import android.view.View
import androidx.lifecycle.Lifecycle
import uz.abubakir_khakimov.recipes.presentation.callback.ActivityViewKeeperCallBack

interface ActivityViewKeeper {

    fun setActivityRootView(rootView: View, lifecycle: Lifecycle)

    fun setCallBack(callBack: ActivityViewKeeperCallBack)

    fun getActivityRootView(): View?
}