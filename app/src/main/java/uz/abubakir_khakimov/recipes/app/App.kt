package uz.abubakir_khakimov.recipes.app

import android.app.Application
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Hawk.init(/* context = */ this).build()
    }
}