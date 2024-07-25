package uz.abubakir_khakimov.recipes.home.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.abubakir_khakimov.recipes.common.utils.Result.Companion.asyncSuccess
import uz.abubakir_khakimov.recipes.common.utils.Result.Companion.error
import uz.abubakir_khakimov.recipes.home.domain.models.Category
import uz.abubakir_khakimov.recipes.home.domain.models.Meal
import uz.abubakir_khakimov.recipes.home.domain.usecase.AddFavouriteMealUseCase
import uz.abubakir_khakimov.recipes.home.domain.usecase.DeleteFavouriteMealByIdUseCase
import uz.abubakir_khakimov.recipes.home.domain.usecase.GetCategoriesUseCase
import uz.abubakir_khakimov.recipes.home.domain.usecase.GetMealsByAreaUseCase
import uz.abubakir_khakimov.recipes.home.domain.usecase.GetMealsByCategoryUseCase
import uz.abubakir_khakimov.recipes.home.domain.usecase.GetMealsByIngredientUseCase
import uz.abubakir_khakimov.recipes.home.domain.usecase.GetMealsBySearchUseCase
import uz.abubakir_khakimov.recipes.home.domain.usecase.IsFavouriteMealExistUseCase
import uz.abubakir_khakimov.recipes.common.managers.Logger
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.presentation.managers.ConnectivityManager
import javax.inject.Inject

typealias ForWho = Int
typealias Position = Int
typealias Tag = Any

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMealsBySearchUseCase: GetMealsBySearchUseCase,
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase,
    private val getMealsByIngredientUseCase: GetMealsByIngredientUseCase,
    private val getMealsByAreaUseCase: GetMealsByAreaUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val isFavouriteMealExistUseCase: IsFavouriteMealExistUseCase,
    private val addFavouriteMealUseCase: AddFavouriteMealUseCase,
    private val deleteFavouriteMealByIdUseCase: DeleteFavouriteMealByIdUseCase,
    private val connectivityManager: ConnectivityManager,
    private val logger: Logger
): ViewModel() {

    private val _getCategories =
        MutableSharedFlow<List<Category>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val getCategories: SharedFlow<List<Category>> = _getCategories.asSharedFlow()

    private val _getMeals =
        MutableSharedFlow<List<Meal>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val getMeals: SharedFlow<List<Meal>> = _getMeals.asSharedFlow()

    private val _searchMeals =
        MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val _isFavouriteMealExist =
        MutableSharedFlow<Pair<Tag, Boolean>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val isFavouriteMealExist: SharedFlow<Pair<Tag, Boolean>> = _isFavouriteMealExist.asSharedFlow()

    private val _selectedCategoryPosition = MutableStateFlow<Position?>(value = 0)
    val selectedCategoryPosition: StateFlow<Position?> = _selectedCategoryPosition.asStateFlow()

    private val _placeHolderVisible =
        MutableSharedFlow<Pair<ForWho, Boolean>>(replay = 2, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val placeHolderVisible: SharedFlow<Pair<ForWho, Boolean>> = _placeHolderVisible.asSharedFlow()

    init {

        _searchMeals
            .debounce(300)
            .onEach {
                if (it.isEmpty()) {
                    changeSelectedCategoryPosition(position = 0)
                    getMealsByCategory(
                        category = getCategories.replayCache.first().first().strCategory ?: return@onEach
                    )
                } else {
                    changeSelectedCategoryPosition(position = null)
                    getMealsBySearch(search = it)
                }
            }.launchIn(viewModelScope)

        changePlaceHolderVisible(visible = Pair(first = FOR_ALL, second = true))
        viewModelScope.launch { delay(timeMillis = 500); getCategories(turnOnPlaceHolder = false) }
    }

    fun getCategories(turnOnPlaceHolder: Boolean = true) {
        viewModelScope.launch {
            if (turnOnPlaceHolder) changePlaceHolderVisible(
                visible = Pair(first = FOR_CATEGORIES, second = true)
            )
            getCategoriesUseCase.invoke().analiseGetCategories()
        }
    }

    private suspend fun Result<List<Category>>.analiseGetCategories() {
        asyncSuccess { data, _ ->
            _getCategories.emit(data ?: return@asyncSuccess)
            changePlaceHolderVisible(visible = Pair(first = FOR_CATEGORIES, second = false))

            if (data.isNotEmpty()) getMealsByCategory(
                category = data.first().strCategory ?: return@asyncSuccess,
                turnOnPlaceHolder = false
            )
        }
        error { error, _ ->
            connectivityManager.checkConnectionError(throwable = error)
        }
    }

    fun getMealsByCategory(category: String, turnOnPlaceHolder: Boolean = true) {
        viewModelScope.launch {
            if (turnOnPlaceHolder) changePlaceHolderVisible(
                visible = Pair(first = FOR_RECIPES, second = true)
            )

            getMealsByCategoryUseCase.invoke(category = category)
                .analiseGetMealsByCategory()
        }
    }

    private suspend fun Result<List<Meal>>.analiseGetMealsByCategory() {
        asyncSuccess { data, _ ->
            _getMeals.emit(data ?: return@asyncSuccess)
            changePlaceHolderVisible(visible = Pair(first = FOR_RECIPES, second = false))
        }
        error { error, _ ->
            connectivityManager.checkConnectionError(throwable = error)
        }
    }

    fun getMealsBySearchWithDeb(search: String) {
        viewModelScope.launch { _searchMeals.emit(value = search) }
    }

    fun getMealsBySearch(search: String, turnOnPlaceHolder: Boolean = true) {
        viewModelScope.launch {
            if (turnOnPlaceHolder) changePlaceHolderVisible(
                visible = Pair(first = FOR_RECIPES, second = true)
            )

            getMealsBySearchUseCase.invoke(search = search)
                .analiseGetMealsBySearch()
        }
    }

    private suspend fun Result<List<Meal>>.analiseGetMealsBySearch() {
        asyncSuccess { data, _ ->
            _getMeals.emit(data ?: return@asyncSuccess)
            changePlaceHolderVisible(visible = Pair(first = FOR_RECIPES, second = false))
        }
        error { error, _ ->
            connectivityManager.checkConnectionError(throwable = error)
        }
    }

    fun isFavouriteMealExist(id: String, tag: Any) {
        viewModelScope.launch {
            isFavouriteMealExistUseCase.invoke(id = id).analiseIsFavouriteMealExist(tag = tag)
        }
    }

    private suspend fun Result<Boolean>.analiseIsFavouriteMealExist(tag: Any) {
        asyncSuccess { data, _ ->
            _isFavouriteMealExist.emit(Pair(first = tag, second = data ?: return@asyncSuccess))
        }
        error { error, _ ->
            connectivityManager.checkConnectionError(throwable = error)
        }
    }

    fun addFavouriteMeal(meal: Meal){
        viewModelScope.launch { addFavouriteMealUseCase.invoke(meal = meal) }
    }

    fun deleteFavouriteMealById(id: String){
        viewModelScope.launch { deleteFavouriteMealByIdUseCase.invoke(id = id) }
    }

    fun categorySelected(category: String, position: Position){
        changeSelectedCategoryPosition(position = position)
        getMealsByCategory(category = category)
    }

    fun changeSelectedCategoryPosition(position: Int?){
        viewModelScope.launch { _selectedCategoryPosition.emit(position) }
    }

    fun changePlaceHolderVisible(visible: Pair<ForWho, Boolean>){
        viewModelScope.launch { _placeHolderVisible.emit(value = visible) }
    }

    companion object{

        const val FOR_ALL = 0
        const val FOR_CATEGORIES = 1
        const val FOR_RECIPES = 2
    }
}