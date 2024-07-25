package uz.abubakir_khakimov.recipes.presentation_impl.managers

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.PopupWindow.OnDismissListener
import android.widget.TextView
import uz.abubakir_khakimov.recipes.presentation.callback.ActivityViewKeeperCallBack
import uz.abubakir_khakimov.recipes.presentation.extensions.ANIMATION_END
import uz.abubakir_khakimov.recipes.presentation.extensions.ANIMATION_START
import uz.abubakir_khakimov.recipes.presentation.extensions.createConfigurationContextByLocale
import uz.abubakir_khakimov.recipes.presentation.extensions.setAnimationListener
import uz.abubakir_khakimov.recipes.presentation.managers.ActivityViewKeeper
import uz.abubakir_khakimov.recipes.presentation.managers.HighPriorityBannerManager
import uz.abubakir_khakimov.recipes.presentation.utils.OnSwipeTouchListener
import uz.abubakir_khakimov.recipes.presentation_impl.R
import uz.abubakir_khakimov.recipes.presentation_impl.databinding.HighPriorityBannerRootLayoutBinding
import java.util.Locale

class HighPriorityBannerManagerImpl(
    private val applicationContext: Context,
    private val activityViewKeeper: ActivityViewKeeper
): HighPriorityBannerManager, ActivityViewKeeperCallBack {

    private var autoDismissEnable: Boolean = true
    private var autoDismissDuration: Long = HighPriorityBannerManager.DEFAULT_BANNER_DURATION
    private var focusable = false
    private var textViewId: Int? = null

    private val contextWithLocale = applicationContext.createConfigurationContextByLocale(
        locale = Locale.getDefault()
    )

    private val binding = HighPriorityBannerRootLayoutBinding
        .inflate(LayoutInflater.from(contextWithLocale), null, false)

    private var popupWindow: PopupWindow = PopupWindow(
        binding.root,
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        focusable
    )

    private val bannerShowAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.banner_show_anim)
    private val bannerDismissAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.banner_dismiss_anim)
    private val bannerRightSwipeAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.banner_right_swipe_anim)
    private val bannerLeftSwipeAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.banner_left_swipe_anim)

    private val handler = Handler(Looper.getMainLooper())
    private val autoDismissRunnable = Runnable {
        dismiss()
    }

    init {

        initTouchListener()
        initAnimationListeners()
    }

    override fun setLayout(layoutRes: Int){
        initializeCustomBannerLayout(layoutRes)
    }

    override fun setAutoDismissDuration(duration: Long){
        autoDismissDuration = duration
    }

    override fun setAutoDismissEnable(enable: Boolean){
        autoDismissEnable = enable
    }

    override fun setFocusable(focusable: Boolean){
        this.focusable = focusable
        popupWindow.isFocusable = focusable
    }

    override fun getContentView(): View = binding.root.getChildAt(0)

    override fun setTextViewId(resId: Int){
        textViewId = resId
    }

    override fun setText(text: String){
        findAndSetText(text)
    }

    override fun setTextAnd(text: String): HighPriorityBannerManager{
        findAndSetText(text)
        return this
    }

    private fun findAndSetText(text: String){
        binding.root.findViewById<TextView>(
            textViewId ?: throw NullPointerException(
                "textViewId is null. " +
                        "First, specify the resource ID using the setTextViewId function."
            )
        ).text = text
    }

    override fun show(gravity: Int){
        if (!popupWindow.isShowing) showBanner(
            activityViewKeeper.getActivityRootView() ?: return, gravity, 0, 0
        )
    }

    override fun show(gravity: Int, x: Int, y: Int){
        if (!popupWindow.isShowing) showBanner(
            activityViewKeeper.getActivityRootView() ?: return, gravity, x, y
        )
    }

    override fun show(anchorView: View, gravity: Int){
        if (!popupWindow.isShowing) showBanner(anchorView, gravity, 0, 0)
    }

    override fun show(anchorView: View, gravity: Int, x: Int, y: Int){
        if (!popupWindow.isShowing) showBanner(anchorView, gravity, x, y)
    }

    override fun dismiss(){
        if (popupWindow.isShowing) binding.root.startAnimation(bannerDismissAnim)
    }

    override fun setOnDismissListener(onDismissListener: OnDismissListener) =
        popupWindow.setOnDismissListener(/* onDismissListener = */ onDismissListener)

    override fun setOnDismissListener(body: () -> Unit) = popupWindow.setOnDismissListener { body() }

    private fun initializeCustomBannerLayout(layoutRes: Int) {
        val layout = LayoutInflater
            .from(contextWithLocale)
            .inflate(layoutRes, binding.root, false)
        binding.root.removeAllViews()
        binding.root.addView(layout)
    }

    private fun showBanner(anchorView: View, gravity: Int, x: Int, y: Int){
        popupWindow.showAtLocation(anchorView, gravity, x, y)
        binding.root.startAnimation(bannerShowAnim)
    }

    private fun dismissBanner(){
        popupWindow.dismiss()
    }

    private fun startAutoDismiss(){
        if (autoDismissEnable) handler.postDelayed(autoDismissRunnable, autoDismissDuration)
    }

    private fun stopAutoDismiss(){
        handler.removeCallbacks(autoDismissRunnable)
    }

    private fun initAnimationListeners(){
        bannerShowAnim.setAnimationListener { _, animState ->
            if (animState == ANIMATION_END) startAutoDismiss()
        }

        bannerDismissAnim.setAnimationListener { _, animState ->
            if (animState == ANIMATION_START) stopAutoDismiss()
            if (animState == ANIMATION_END) dismissBanner()
        }

        bannerRightSwipeAnim.setAnimationListener { _, animState ->
            if (animState == ANIMATION_START) stopAutoDismiss()
            if (animState == ANIMATION_END) dismissBanner()
        }

        bannerLeftSwipeAnim.setAnimationListener { _, animState ->
            if (animState == ANIMATION_START) stopAutoDismiss()
            if (animState == ANIMATION_END) dismissBanner()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initTouchListener(){
        binding.root.setOnTouchListener(object: OnSwipeTouchListener(applicationContext){
            override fun onSwipeTop() {
                super.onSwipeTop()
                binding.root.startAnimation(bannerDismissAnim)
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                binding.root.startAnimation(bannerRightSwipeAnim)
            }

            override fun onSwipeLeft() {
                super.onSwipeLeft()
                binding.root.startAnimation(bannerLeftSwipeAnim)
            }
        })
    }

    override fun activityViewDestroyed() {
        dismiss()
    }
}