package uz.abubakir_khakimov.recipes.presentation_impl.managers

import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import uz.abubakir_khakimov.recipes.presentation.callback.ActivityViewKeeperCallBack
import uz.abubakir_khakimov.recipes.presentation.managers.ActivityViewKeeper

class ActivityViewKeeperImpl: ActivityViewKeeper, DefaultLifecycleObserver {

    private var activityRootView: View? = null
    private var activityLifecycle: Lifecycle? = null
    private var callBack: ActivityViewKeeperCallBack? = null

    override fun setActivityRootView(rootView: View, lifecycle: Lifecycle){
        activityRootView = rootView
        activityLifecycle = lifecycle

        startControlActivityLifecycle()
    }

    override fun setCallBack(callBack: ActivityViewKeeperCallBack){
        this.callBack = callBack
    }

    override fun getActivityRootView(): View? = activityRootView

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)

        stopControlActivityLifecycle()

        activityRootView = null
        activityLifecycle = null

        callBack?.activityViewDestroyed()
    }

    private fun startControlActivityLifecycle(){
        activityLifecycle?.addObserver(this)
    }

    private fun stopControlActivityLifecycle(){
        activityLifecycle?.removeObserver(this)
    }
}