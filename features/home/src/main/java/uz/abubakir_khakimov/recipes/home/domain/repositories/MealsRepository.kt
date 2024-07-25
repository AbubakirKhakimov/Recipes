package uz.abubakir_khakimov.recipes.home.domain.repositories

import uz.abubakir_khakimov.recipes.home.domain.models.Meal
import uz.abubakir_khakimov.recipes.common.utils.Result

interface MealsRepository {

    suspend fun getMealsByCategory(category: String): Result<List<Meal>>

    suspend fun getMealsByArea(area: String): Result<List<Meal>>

    suspend fun getMealsByIngredient(ingredient: String): Result<List<Meal>>

    suspend fun getMealsBySearch(search: String): Result<List<Meal>>

    suspend fun isFavouriteMealExist(id: String): Result<Boolean>

    suspend fun addFavouriteMeal(meal: Meal)

    suspend fun deleteFavouriteMealById(id: String)
}