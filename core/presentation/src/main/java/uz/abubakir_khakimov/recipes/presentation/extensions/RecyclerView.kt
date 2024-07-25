package uz.abubakir_khakimov.recipes.presentation.extensions

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addOnScrolledListener(body: (recyclerView: RecyclerView, dx: Int, dy: Int) -> Unit) =
    object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) =
            body(recyclerView, dx, dy)
    }.also { addOnScrollListener(/* listener = */ it) }