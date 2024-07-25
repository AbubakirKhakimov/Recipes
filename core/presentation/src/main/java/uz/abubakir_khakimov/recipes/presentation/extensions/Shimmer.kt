package uz.abubakir_khakimov.recipes.presentation.extensions

import com.facebook.shimmer.ShimmerFrameLayout

fun ShimmerFrameLayout.changeShimmerState(state: Boolean){
    if (state) startShimmer() else stopShimmer()
}