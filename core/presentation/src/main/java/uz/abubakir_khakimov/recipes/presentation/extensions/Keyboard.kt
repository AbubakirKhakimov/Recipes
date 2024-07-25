package uz.abubakir_khakimov.recipes.presentation.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.closeKeyboardWithFocus() {
    clearFocus()
    closeKeyboard()
}

fun EditText.openKeyboardWithFocus(){
    requestFocus()
    openKeyboard()
}

fun EditText.closeKeyboard() {
    val inputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun EditText.openKeyboard(){
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}