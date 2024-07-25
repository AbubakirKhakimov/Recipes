package uz.abubakir_khakimov.recipes.presentation.extensions

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.onPageChangeCallback(
    body: (position: Int) -> Unit
){
    val viewPagerPageChangeCallBack = object: ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) { body(position) }
    }
    registerOnPageChangeCallback(/* callback = */ viewPagerPageChangeCallBack)
}