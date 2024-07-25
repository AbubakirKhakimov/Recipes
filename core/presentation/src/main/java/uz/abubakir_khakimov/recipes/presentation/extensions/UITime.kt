package uz.abubakir_khakimov.recipes.presentation.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentYear(): String = SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())