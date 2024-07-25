package uz.abubakir_khakimov.recipes.navigation

import android.content.Context
import android.view.MenuInflater
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.qualifiers.ApplicationContext
import java.security.InvalidParameterException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private var navHostFragmentsMap = HashMap<String, NavHostFragment>()

    fun selectNavDestination(
        @MenuRes menuRes: Int,
        needToSelectItemId: Int,
        navController: NavController
    ){
        val inflater = MenuInflater(/* context = */ context)
        val menu = PopupMenu(/* context = */ context, /* anchor = */ null).menu
        inflater.inflate(/* menuRes = */ menuRes, /* menu = */ menu)

        NavigationUI.onNavDestinationSelected(
            item = menu.findItem(/* id = */ needToSelectItemId)
                ?: throw IllegalArgumentException(NEED_TO_SELECT_ITEM_ID_NOT_FOUND_MESSAGE),
            navController = navController
        )
    }

    fun setNavHostFragment(fragmentActivity: FragmentActivity, key: String, resource: Int) =
        saveNavHostFragment(
            fragmentManager = fragmentActivity.supportFragmentManager,
            key = key,
            resource = resource
        )

    fun setNavHostFragment(fragmentManager: FragmentManager, key: String, resource: Int) =
        saveNavHostFragment(
            fragmentManager = fragmentManager,
            key = key,
            resource = resource
        )

    fun getNavController(key: String): NavController? =
        navHostFragmentsMap[key]?.navController

    fun getDefaultNavController(): NavController? =
        navHostFragmentsMap[DEFAULT_NAV_HOST]?.navController

    fun getMainNavController(): NavController? =
        navHostFragmentsMap[MAIN_NAV_HOST]?.navController

    fun clearAll() {
        navHostFragmentsMap.clear()
    }

    fun clear(key: String){
        navHostFragmentsMap.remove(key)
    }

    private fun saveNavHostFragment(fragmentManager: FragmentManager, key: String, resource: Int){
        navHostFragmentsMap[key] = getNavHostFragment(
            fragmentManager = fragmentManager,
            resource = resource
        ) ?: throw InvalidParameterException("Given resource invalid! Can not cast NavHostFragment")
    }

    private fun getNavHostFragment(
        fragmentManager: FragmentManager,
        resource: Int
    ): NavHostFragment? = fragmentManager.findFragmentById(resource) as NavHostFragment?

    companion object{

        const val DEFAULT_NAV_HOST = "default_nav_host"
        const val MAIN_NAV_HOST = "main_nav_host"

        private const val NEED_TO_SELECT_ITEM_ID_NOT_FOUND_MESSAGE =
            "The specified needToSelectItemId parameter was not found!"
    }
}