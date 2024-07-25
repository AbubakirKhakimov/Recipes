package uz.abubakir_khakimov.recipes.home.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import uz.abubakir_khakimov.recipes.home.R
import uz.abubakir_khakimov.recipes.home.databinding.FragmentHomeBinding
import uz.abubakir_khakimov.recipes.home.domain.models.Meal
import uz.abubakir_khakimov.recipes.home.presentation.adapters.CategoriesAdapter
import uz.abubakir_khakimov.recipes.home.presentation.adapters.MealsAdapter
import uz.abubakir_khakimov.recipes.home.presentation.viewmodels.ForWho
import uz.abubakir_khakimov.recipes.home.presentation.viewmodels.HomeViewModel
import uz.abubakir_khakimov.recipes.presentation.extensions.changeShimmerState
import uz.abubakir_khakimov.recipes.presentation.extensions.collectWithLifeCircle
import uz.abubakir_khakimov.recipes.presentation.extensions.px
import uz.abubakir_khakimov.recipes.presentation.managers.PlaceHolderManager
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :
    Fragment(R.layout.fragment_home),
    CategoriesAdapter.CallBack,
    MealsAdapter.CallBack
{

    @Inject lateinit var placeHolderManager: PlaceHolderManager

    private val binding by viewBinding (FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private val categoriesAdapter: CategoriesAdapter by lazy {
        CategoriesAdapter(context = requireContext(), callBack = this)
    }
    private val mealsAdapter: MealsAdapter by lazy {
        MealsAdapter(context = requireContext(), callBack = this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            updateUi()
            attachListeners()
        }

        flowCollectManager()
    }

    private fun FragmentHomeBinding.updateUi(){
        categoriesRV.adapter = categoriesAdapter
        recipesRV.adapter = mealsAdapter

        placeHolderManager.createByScreenSize(
            context = requireContext(),
            itemResource = R.layout.categories_place_holder,
            parentLayout = categoriesShimmerInclude.shimmerContainer,
            beforeCleanUp = true,
            layoutParams = PlaceHolderManager.LayoutParams(paddingStart = 12.px, paddingEnd = 12.px)
        )

        placeHolderManager.createByCustomCount(
            context = requireContext(),
            itemResource = R.layout.meals_place_holder,
            parentLayout = recipesShimmerInclude.shimmerContainer,
            beforeCleanUp = true,
            placeHolderCount = 6,
            layoutParams = PlaceHolderManager.LayoutParams(paddingTop = 4.px, paddingBottom = 4.px)
        )
    }

    private fun FragmentHomeBinding.attachListeners(){

        search.addTextChangedListener { editable ->
            viewModel.getMealsBySearchWithDeb(search = editable.toString())
        }
    }

    private fun flowCollectManager(){
        getCategoriesCollect()
        getMealsCollect()
        isFavouriteMealExistCollect()
        selectedCategoryPositionCollect()
        placeHolderVisibleCollect()
    }

    private fun getCategoriesCollect() = viewModel.getCategories
        .onEach {
            categoriesAdapter.submitList(/* list = */ it)
        }.collectWithLifeCircle(lifecycleOwner = viewLifecycleOwner)

    private fun getMealsCollect() = viewModel.getMeals
        .onEach {
            mealsAdapter.submitList(/* list = */ it)
        }.collectWithLifeCircle(lifecycleOwner = viewLifecycleOwner)

    private fun isFavouriteMealExistCollect() = viewModel.isFavouriteMealExist
        .onEach { (tag, isExist) ->
            if (isExist) mealsAdapter.addFavouriteMeal(position = tag as Int)
        }.collectWithLifeCircle(lifecycleOwner = viewLifecycleOwner)

    private fun selectedCategoryPositionCollect() = viewModel.selectedCategoryPosition
        .onEach {
            categoriesAdapter.changeSelectedPosition(position = it)
        }.collectWithLifeCircle(lifecycleOwner = viewLifecycleOwner)

    private fun placeHolderVisibleCollect() = viewModel.placeHolderVisible
        .onEach {
            changePlaceHolderVisible(visible = it)
        }.collectWithLifeCircle(lifecycleOwner = viewLifecycleOwner)

    private fun changePlaceHolderVisible(visible: Pair<ForWho, Boolean>) {
        when (visible.first) {
            HomeViewModel.FOR_ALL, HomeViewModel.FOR_CATEGORIES -> {
                binding.categoriesShimmerInclude.root.isVisible = visible.second
                binding.categoriesRV.isGone = visible.second
                binding.categoriesShimmerInclude.root.changeShimmerState(visible.second)
            }
        }

        when (visible.first) {
            HomeViewModel.FOR_ALL, HomeViewModel.FOR_RECIPES -> {
                binding.recipesShimmerInclude.root.isVisible = visible.second
                binding.recipesRV.isGone = visible.second
                binding.recipesShimmerInclude.root.changeShimmerState(visible.second)
            }
        }
    }

    override fun categorySelectedListener(category: String, position: Int) {
        viewModel.categorySelected(category = category, position = position)
    }

    override fun isFavouriteMealExist(id: String, position: Int) {
        viewModel.isFavouriteMealExist(id = id, tag = position)
    }

    override fun addToFavourite(meal: Meal) {
        viewModel.addFavouriteMeal(meal = meal)
    }

    override fun deleteFromFavourite(id: String) {
        viewModel.deleteFavouriteMealById(id = id)
    }
}