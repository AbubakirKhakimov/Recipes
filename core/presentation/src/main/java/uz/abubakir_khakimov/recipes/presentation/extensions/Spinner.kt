package uz.abubakir_khakimov.recipes.presentation.extensions

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

fun Spinner.onItemSelectedListener(
    body: (parent: AdapterView<*>?, view: View?, position: Int, id: Long) -> Unit
){
    onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            body(parent, view, position, id)
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}