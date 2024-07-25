package uz.abubakir_khakimov.recipes.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import uz.abubakir_khakimov.recipes.navigation.NavigationManager.Companion.MAIN_NAV_HOST
import uz.abubakir_khakimov.recipes.R
import uz.abubakir_khakimov.recipes.databinding.ActivityMainBinding
import uz.abubakir_khakimov.recipes.presentation.managers.ActivityViewKeeper
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var activityViewKeeper: ActivityViewKeeper
    @Inject lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityViewKeeper.setActivityRootView(
            rootView = binding.root,
            lifecycle = lifecycle
        )

        navigationManager.setNavHostFragment(
            fragmentActivity = this,
            key = MAIN_NAV_HOST,
            resource = R.id.mainFragmentContainerView
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        navigationManager.clearAll()
    }
}